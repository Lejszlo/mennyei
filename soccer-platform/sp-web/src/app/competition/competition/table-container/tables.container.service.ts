import {Club, MatchResource, Row, WinnerType} from "../competition.component.types";
import {Injectable} from "@angular/core";

@Injectable()
export class TablesContainerService {

    getNormalTable(matches: MatchResource[]): Row[] {
        let rows: Row[] = [];
        matches.map((match: MatchResource) => {
            let homeRow = rows.find(row => row.club.clubId === match.homeClubDocumentResource.clubId);
            this.createOrUpdateRow(homeRow, match, rows, match.homeClubDocumentResource);
            let awayRow = rows.find(row => row.club.clubId === match.awayClubDocumentResource.clubId);
            this.createOrUpdateRow(awayRow, match, rows, match.awayClubDocumentResource);
        });

        return rows.sort((a: Row, b: Row) => {
            return b.point - a.point;
        });
    }

    getHomeTable(matches: MatchResource[]): Row[] {
        let rows: Row[] = [];
        matches.map((match: MatchResource) => {
            let homeRow = rows.find(row => row.club.clubId === match.homeClubDocumentResource.clubId);
            this.createOrUpdateRow(homeRow, match, rows, match.homeClubDocumentResource);
        });

        return rows.sort((a: Row, b: Row) => {
            return b.point - a.point;
        });
    }

    getAwayTable(matches: MatchResource[]): Row[] {
        let rows: Row[] = [];
        matches.map((match: MatchResource) => {
            let awayRow = rows.find(row => row.club.clubId === match.awayClubDocumentResource.clubId);
            this.createOrUpdateRow(awayRow, match, rows, match.awayClubDocumentResource);
        });

        return rows.sort((a: Row, b: Row) => {
            return b.point - a.point;
        });
    }



    private createOrUpdateRow(row: Row, match: MatchResource, rows: Row[], club: Club) {
        if (row) {
            this.updateRow(row, match);
        } else {
            let row = new Row();
            row.club = club;
            this.updateRow(row, match);
            rows.push(row);
        }
    }

    updateRow(row: Row, match: MatchResource) {
        row.playedMatches += 1;
        if(row.club.clubId === match.homeClubDocumentResource.clubId) {
            row.scoredGoals += match.homeGoalAmount;
            row.concerdGoals += match.awayGoalAmount;

            if(match.winnerType === "HOME") {
                row.win += 1;
            }
            if(match.winnerType === "AWAY") {
                row.lose += 1;
            }
            if(match.winnerType === "DRAW") {
                row.draw += 1;
            }

        } else {
            row.scoredGoals += match.awayGoalAmount;
            row.concerdGoals += match.homeGoalAmount;

            if(match.winnerType === "HOME") {
                row.lose += 1;
            }
            if(match.winnerType === "AWAY") {
                row.win += 1;
            }
            if(match.winnerType === "DRAW") {
                row.draw += 1;
            }
        }

        row.point = row.win * 3;
    }

}
