import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {MatchResource, TurnResource} from "../competition.component.types";
import {CompetitionService} from "../competition.component.service";
import {Observable} from "rxjs/Observable";

@Component({
  selector: 'app-turn',
  templateUrl: './turn.component.html',
  styleUrls: ['./turn.component.scss']
})
export class TurnComponent implements OnInit, OnChanges {

  @Input('turn')
  selectedTurn: TurnResource;
  matches: Observable<MatchResource[]>;
  displayedColumns: string[] = ['date', 'homeClub', 'homeGoals', 'awayGoals', 'awayClub'];

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
      this.matches = this.competitionService
        .getMatchesOfTurns(this.selectedTurn);
    }
  }

}
