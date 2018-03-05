import { Component, OnInit } from '@angular/core';
import 'reflect-metadata'
import { CompetitionService } from './competition.service';
import {Club, CompetitionResource, Stage, TurnResource} from "./competition.types";
import {Observable} from "rxjs/Observable";
import {TurnComponent} from "./turn/turn.component";

@Component({
    selector: 'sp-competition',
    templateUrl: './competition.component.html'
})
export class CompetitionComponent implements OnInit {
    competitionResource : CompetitionResource;
    stages: Stage[];
    clubs : Club[];
    selectedStage: Stage;
    turns: TurnResource[];
    selectedTurn: TurnResource;

    constructor (private competitionService : CompetitionService ) {
    }

    ngOnInit(): void {
        this.competitionService
            .getCompetition()
            .subscribe(data => {
                this.competitionResource = data;
                this.getStages(this.competitionResource);
        });
    }

    getStages(competitionResource: CompetitionResource): void {
        if(this.competitionResource !== undefined) {
            this.competitionService
                .getStages(competitionResource)
                .subscribe(data => {
                    this.stages = data;
                    this.selectedStage = this.stages[0];

                    this.getClubs(this.selectedStage);
                    this.getTurns(this.selectedStage);
                });
        }
    }

    getTurns(stage: Stage): void {
        if(this.competitionResource !== undefined) {
            this.competitionService
                .getTurns(stage)
                .subscribe(data => {
                    this.turns = data;
                    this.selectedTurn = this.turns[0];
                });
        }
    }

    getClubs(stage: Stage): void {
        this.competitionService
            .getClubs(stage)
            .subscribe(data => {
                this.clubs = data;
        });
    }

}