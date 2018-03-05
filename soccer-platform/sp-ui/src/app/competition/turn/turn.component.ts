import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import 'reflect-metadata'
import {MatchResource, TurnResource} from "../competition.types";
import {CompetitionService} from "../competition.service";

@Component({
    selector: 'sp-turn',
    templateUrl: './turn.component.html'
})
export class TurnComponent implements OnInit, OnChanges {
    @Input('turn')
    selectedTurn: TurnResource;
    matches: MatchResource[];
    private _selectedTurn: TurnResource;

    constructor(private competitionService : CompetitionService ) {

    }

    ngOnInit(): void {
    }

    ngOnChanges(changes: SimpleChanges): void {
        if(changes.selectedTurn) {
            this._selectedTurn = changes.selectedTurn.currentValue;
            this.getMatches();
        }
    }

    getMatches(): void {
        if(this.selectedTurn) {
            this.competitionService
                .getMatchesOfTurns(this.selectedTurn)
                .subscribe(data => {
                    this.matches = data;
                });
        }
    }

}