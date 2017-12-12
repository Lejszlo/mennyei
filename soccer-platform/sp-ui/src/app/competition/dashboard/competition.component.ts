import { Component, OnInit } from '@angular/core';
import 'reflect-metadata'
import { CompetitionService } from '../competition.service';
import {Club, CompetitionResource} from "../Competition.types";

@Component({
    selector: 'sp-competition',
    templateUrl: './competition.component.html'
})
export class CompetitionComponent implements OnInit {
    competitionResource : CompetitionResource;
    clubs : Promise<Club[]>;

    constructor (private competitionService : CompetitionService ) {
    }

    ngOnInit(): void {
        this.competitionService.getCompetition().then(data => {
            this.competitionResource = data;
            this.clubs = this.getClubs();
        });
    }

    getClubs(): Promise<Club[]> {
        if(this.competitionResource !== undefined) {
            return this.competitionService.getClubs(this.competitionResource);
        }
    }

}