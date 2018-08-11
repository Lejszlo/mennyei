import {Injectable} from '@angular/core';
import {Observable} from "rxjs/Observable";
import {HttpClient} from "@angular/common/http";
import {
  Club,
  CompetitionResource,
  MatchResource,
  Stage,
  TableResource,
  TurnResource
} from "./competition.component.types";
import {map} from "rxjs/operators";

@Injectable()
export class CompetitionService {
  private competitionUrl = '/api/competition/1';

  constructor(private http: HttpClient) {
  }

  getCompetition(): Observable<CompetitionResource> {
    return this.http.get<CompetitionResource>(this.competitionUrl);
  }

  getClubs(stage: Stage): Observable<Club[]> {
    return this.http.get<Club[]>(stage._links.clubs.href)
      .pipe(map((result: any) => {
        return result._embedded.clubDocumentResourceList;
      }));
  }

  getTable(stage: Stage): Observable<TableResource> {
    return this.http.get<TableResource>(stage._links.table.href)
      .pipe(map((result: any) => {
        return result;
      }));
  }

  getTurns(stage: Stage): Observable<TurnResource[]> {
    return this.http.get<TurnResource[]>(stage._links.turns.href)
      .pipe(map((result: any) => {
        return result._embedded.turnDocumentResourceList;
      }));
  }

  getMatchesOfTurns(turnResource: TurnResource): Observable<MatchResource[]> {
    return this.http.get<MatchResource[]>(turnResource._links.matches.href)
      .pipe(map((result: any) => {
        return result;
      }));
  }
}
