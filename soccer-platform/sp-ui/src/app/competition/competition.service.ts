import { Injectable } from '@angular/core';
import { Http } from "@angular/http";
import {Club, CompetitionResource, Row, TableResource} from "./Competition.types";

@Injectable()
export class CompetitionService {
    private competitionUrl = 'api/competition/1';

    constructor(private http: Http) { }

    getCompetition(): Promise<CompetitionResource> {
       return this.http.get(this.competitionUrl)
           .map((response) => response.json() as CompetitionResource)
           .toPromise()
           .catch(this.handleError);
    }

    getClubs(competitionResource: CompetitionResource): Promise<Club[]> {
        return this.http.get(competitionResource._links.clubs.href)
            .map((response) => response.json() as Club[])
            .toPromise()
            .catch(this.handleError);
    }

    getTable(competitionResource: CompetitionResource): Promise<TableResource[]> {
        return this.http.get(competitionResource._links.tables.href)
            .map((response) => response.json() as TableResource[])
            .toPromise()
            .catch(this.handleError);
    }

    private extractData(response : any) : CompetitionResource {
        return response.json() as CompetitionResource;
    }

    private handleError(error: any): Promise<any> {
        console.error('An error occurred', error); // for demo purposes only
        return Promise.reject(error.message || error);
    }
}
