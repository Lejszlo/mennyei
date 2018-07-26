import { Injectable } from '@angular/core';
import { Observable } from "rxjs/Observable";
import { HttpClient } from "@angular/common/http";
import {
  Club,
  CompetitionResource,
  MatchResource,
  Stage,
  TableResource,
  TurnResource
} from "./competition.component.types";

@Injectable()
export class CompetitionService  {
    private competitionUrl = '/api/competition/1';

    constructor(private http: HttpClient) { }

    getCompetition(): Observable<CompetitionResource> {
       return this.http.get<CompetitionResource>(this.competitionUrl);
    }

    getStages(competitionResource: CompetitionResource): Observable<Stage[]> {
        return this.http.get<Stage[]>(competitionResource._links.stages.href);
    }

    getClubs(stage: Stage): Observable<Club[]> {
        return this.http.get<Club[]>(stage.links.clubs.href);
    }

    getTable(stage: Stage): Observable<TableResource> {
        return this.http.get<TableResource>(stage.links.table.href);
    }

    getTurns(stage: Stage): Observable<TurnResource[]> {
        return this.http.get<TurnResource[]>(stage.links.turns.href);
    }

    getMatchesOfTurns(turnResource: TurnResource): Observable<MatchResource[]> {
        return this.http.get<MatchResource[]>(turnResource._links.matches.href);
    }
}
