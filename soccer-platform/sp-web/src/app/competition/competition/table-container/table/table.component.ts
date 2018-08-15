import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {MatchResource, Row, TableType} from "../../competition.component.types";
import {TablesContainerService} from "../tables.container.service";

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.scss']
})
export class TableComponent implements OnInit, OnChanges {
  @Input("matches")
  public matches: MatchResource[];
  @Input("type")
  public type: string;
  private _matches: MatchResource[];
  public rows: Row[];
  public displayedColumns: string[] = ['club', 'playedMatches', 'win', 'draw', 'lose', 'scoredGoals', 'concerdGoals', 'goalDifferent', 'point'];

  constructor(private tableContainerService: TablesContainerService) { }

  ngOnInit() {

  }

  ngOnChanges(changes: SimpleChanges): void {
    if(changes.matches) {
      this._matches = changes.matches.currentValue;
      this.getRows();
    }
  }

  private getRows() {
    if(this.type === 'NORMAL') {
      this.rows = this.tableContainerService.getNormalTable(this._matches);
    } else if(this.type === 'HOME') {
      this.rows = this.tableContainerService.getHomeTable(this._matches);
    } else if(this.type === 'AWAY') {
      this.rows = this.tableContainerService.getAwayTable(this._matches);
    }

  }
}
