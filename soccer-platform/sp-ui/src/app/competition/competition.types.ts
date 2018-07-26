export class Club {
    fullName: string;
    clubId: string;
    name: string;
    urlName: string;

    constructor(clubId: string) {
        this.clubId = clubId;
    }
}

export interface Stage {
    _links : any;
    name: string;
    uuiD: number;
    stageRuleSet: StageRuleSet;
}

export interface StageRuleSet {
    numberOfTeams: number;
    pointsForWin: number;
    pointsForDraw: number;
    pointsForLose: number;
    numberOfMatches: number;
    subsNamed: number;
    subsUsed: number;
    relegation: number;
    promotion: number;
    yellowCardLimit: number;
    yellowCardsBan: number;
    redCardsBan: number;
    oneHalfMinutes: number;
    hasOverTime: number;
    numberOfHalfs: number;
    oneHalfOverTimeMinutes: number;
    hasPenalties: boolean;
}

export interface CompetitionResource {
    competitionInfo : CompetitionInfo;
    _links : any;
}

export interface CompetitionInfo {
}

export interface TableResource {
    matches: MatchResource[];
}

export interface TurnResource {
    index: number;
    interval: string;
    _links : any;
}

export class MatchResource {
    homeClubDocumentResource: Club;
    awayClubDocumentResource: Club;
    homeClubId: string;
    awayClubId: string;
    homeGoalAmount: number;
    awayGoalAmount: number;
    matchDate: string;
    homeYellowCardAmount: number;
    awayYellowCardAmount: number;
    homeRedCardAmount: number;
    awayRedCardAmount: number;
    played: boolean;
    winnerType: string;
    _links : any;

}

export enum WinnerType {
    HOME,
    AWAY,
    DRAW
}

export class Row {
    club: Club;
    concerdGoals: number = 0;
    draw: number = 0;
    lose: number = 0;
    playedMatches: number = 0;
    point: number = 0;
    scoredGoals: number = 0;
    win: number = 0;
}