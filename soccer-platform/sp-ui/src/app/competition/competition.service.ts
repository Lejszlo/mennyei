import { Injectable } from '@angular/core';
import {Club, CompetitionResource, MatchResource, Stage, TableResource, TurnResource} from "./competition.types";
import { Observable } from "rxjs/Observable";
import {Http} from "@angular/http";

@Injectable()
export class CompetitionService  {
    private competitionUrl = '/organizer-query/api/competition/1';

    constructor(private http: Http) { }

    getCompetition(): Observable<CompetitionResource> {
       return this.http.get(this.competitionUrl)
           .map((response) => response.json() as CompetitionResource);
    }

    getStages(competitionResource: CompetitionResource): Observable<Stage[]> {
        return this.http.get(competitionResource._links.stages.href)
            .map((response) => response.json()._embedded.stageDocumentResourceList as Stage[]);
    }

    getClubs(stage: Stage): Observable<Club[]> {
        return this.http.get(stage._links.clubs.href)
            .map((response) => response.json()._embedded.clubDocumentResourceList as Club[]);
    }

    getTable(stage: Stage): Observable<TableResource> {
        return this.http.get(stage._links.table.href)
            .map((response) => response.json() as TableResource);
    }

    getTurns(stage: Stage): Observable<TurnResource[]> {
        return this.http.get(stage._links.turns.href)
            .map((response) => response.json()._embedded.turnDocumentResourceList as TurnResource[]);
    }

    getMatchesOfTurns(turnResource: TurnResource): Observable<MatchResource[]> {
        return this.http.get(turnResource._links.matches.href)
            .map((response) => response.json()._embedded.matchDocumentResourceList as MatchResource[]);
    }
}
