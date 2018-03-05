import {Component, Input, OnChanges, OnInit, SimpleChange, SimpleChanges} from '@angular/core';
import 'reflect-metadata'
import { CompetitionService } from '../competition.service';
import {CompetitionResource, Row, Stage, TurnResource} from '../competition.types';

@Component({
    selector: 'sp-table',
    templateUrl: './table.component.html'
})
export class TableComponent implements OnChanges {
    @Input()
    competitionResource : CompetitionResource;
    @Input()
    selectedStage: Stage;
    _selectedStage: Stage;
    rows: Row[];

    constructor (private competitionService : CompetitionService ) {
    }

    ngOnChanges(changes: SimpleChanges) {
        if(changes.selectedStage) {
            const selectedStage: SimpleChange = changes.selectedStage;
            this._selectedStage = selectedStage.currentValue;
            this.getTable();
        }
    }

    getTable(): void {
        if(this.selectedStage) {
            this.competitionService
                .getTable(this.selectedStage)
                .subscribe(data => {
                    this.rows = data.rows;
                });
        }
    }

}