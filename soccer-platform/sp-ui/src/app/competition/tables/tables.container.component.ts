import {Component, Input, OnChanges, SimpleChange, SimpleChanges} from '@angular/core';
import 'reflect-metadata'
import { CompetitionService } from '../competition.service';
import {CompetitionResource, MatchResource, Row, Stage} from '../competition.types';
import {TablesContainerService} from "./tables.container.service";

@Component({
    selector: 'sp-table-container',
    templateUrl: './tables.container.component.html'
})
export class TablesContainerComponent implements OnChanges {
    @Input()
    normalTable: boolean = true;
    @Input()
    homeTable: boolean = true;
    @Input()
    awayTable: boolean = true;
    @Input()
    competitionResource: CompetitionResource;
    @Input()
    selectedStage: Stage;
    _selectedStage: Stage;
    rows: Row[];
    matches: MatchResource[];
    activeTab: number = 0;

    constructor (private competitionService : CompetitionService,
                 private tablesContainerService: TablesContainerService) {
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
                    this.matches = data.matches;
                    this.rows = this.tablesContainerService.getNormalTable(this.matches);
                });
        }
    }

    getActiveClass(tabIndex: number) {
        if(this.activeTab === tabIndex) {
            return "active";
        }

        return "";
    }

    showNormalTable(): void {
        this.rows = this.tablesContainerService.getNormalTable(this.matches);
        this.activeTab = 0;
    }

    showHomeTable(): void {
        this.rows = this.tablesContainerService.getHomeTable(this.matches);
        this.activeTab = 1;
    }

    showAwayTable(): void {
        this.rows = this.tablesContainerService.getAwayTable(this.matches);
        this.activeTab = 2;
    }

    getPromotedPlaces(): number {
        if(this.selectedStage) {
            return this.selectedStage.stageRuleSet.promotion;
        }
    }

    getRelegationPlaces(): number {
        if(this.selectedStage) {
            return this.selectedStage.stageRuleSet.relegation;
        }
    }

}