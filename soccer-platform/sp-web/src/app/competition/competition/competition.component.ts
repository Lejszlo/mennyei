import {Component, OnChanges, OnInit} from '@angular/core';
import {Club, CompetitionInfo, Stage, TurnResource} from "./competition.component.types";
import {CompetitionService} from "./competition.component.service";

@Component({
  selector: 'app-competition',
  templateUrl: 'competition.component.html',
  styleUrls: ['./competition.component.scss']
})
export class CompetitionComponent implements OnInit {
  public competitionInfo: CompetitionInfo;
  public stages: Stage[];
  public clubs: Club[];
  public turns: TurnResource[];

  constructor(private competitionService: CompetitionService) {
  }

  ngOnInit() {
    this.competitionService.getCompetition()
      .subscribe(competitionResource => {
        this.competitionInfo = competitionResource.competitionInfo;
        this.stages = competitionResource.stages;
        this.getClubs();
        this.turns = this.stages[0].turns.sort((t1, t2) => t1.index - t2.index);
      });
  }

  private getClubs() {
    this.competitionService.getClubs(this.stages[0])
      .subscribe(clubs => {
        this.clubs = clubs;
      })
  }
}
