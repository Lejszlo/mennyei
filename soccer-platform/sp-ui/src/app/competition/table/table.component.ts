import { Component } from '@angular/core';
import 'reflect-metadata'
import { CompetitionService } from '../competition.service';
import { CompetitionResource, Row, TableResource } from '../Competition.types';

@Component({
    selector: 'sp-table',
    templateUrl: './table.component.html'
})
export class TableComponent {
    competitionResource : CompetitionResource;
    tableResources: Promise<TableResource[]>;

    constructor (private competitionService : CompetitionService ) {
    }

    ngOnInit(): void {
        this.competitionService.getCompetition().then(data => {
            this.competitionResource = data;
            this.tableResources = this.getTable();
        });
    }

    getTable(): Promise<TableResource[]> {
        if(this.competitionResource) {
            return this.competitionService.getTable(this.competitionResource);
        }
    }

}