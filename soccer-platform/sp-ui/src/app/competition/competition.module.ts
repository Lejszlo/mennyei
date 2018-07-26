import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common'
import { CompetitionService } from './competition.service';
import { HttpModule }    from '@angular/http';
import { TablesContainerComponent } from './tables/tables.container.component';
import { CompetitionComponent } from './competition.component';
import { ClubDetailsComponent } from '../club/details/club.details.component';
import { FirstLetterPipe } from "../pipes/first.letter.pipe";
import { MatchComponent } from "./match/match.component";
import { TurnComponent } from "./turn/turn.component";
import {TableComponent} from "./tables/table/table.component";
import {TablesContainerService} from "./tables/tables.container.service";

@NgModule({
    imports: [
        HttpModule,
        CommonModule
    ],
    declarations: [
        TablesContainerComponent,
        TableComponent,
        CompetitionComponent,
        ClubDetailsComponent,
        MatchComponent,
        TurnComponent,

        FirstLetterPipe
    ],
    providers: [
        CompetitionService,
        TablesContainerService
    ]
})
export class CompetitionModule { }