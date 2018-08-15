import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {MatchResource, Stage} from "../competition.component.types";
import {CompetitionService} from "../competition.component.service";

@Component({
  selector: 'app-table-container',
  templateUrl: './table-container.component.html',
  styleUrls: ['./table-container.component.scss']
})
export class TableContainerComponent implements OnInit, OnChanges {
  @Input("stage")
  public stage: Stage;
  public _stage: Stage;
  public matches: MatchResource[];

  constructor(private competitionService: CompetitionService ) { }

  ngOnInit() {
  }

  ngOnChanges(changes: SimpleChanges): void {
    if(changes.stage) {
      this._stage = changes.stage.currentValue;
      this.getMatches();
    }
  }

  private getMatches() {
    this.competitionService.getTable(this._stage)
      .subscribe(value => this.matches = value.matches);
  }
}
