import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common'
import { CompetitionService } from './competition.service';
import { HttpModule }    from '@angular/http';
import { TableComponent } from './table/table.component';
import { CompetitionComponent } from './competition.component';
import { ClubDetailsComponent } from '../club/details/club.details.component';
import {FirstLetterPipe} from "../pipes/first.letter.pipe";
import {MatchComponent} from "./match/match.component";
import {TurnComponent} from "./turn/turn.component";

@NgModule({
    imports: [
        HttpModule,
        CommonModule
    ],
    declarations: [
        TableComponent,
        CompetitionComponent,
        ClubDetailsComponent,
        MatchComponent,
        TurnComponent,

        FirstLetterPipe
    ],
    providers: [
        CompetitionService
    ]
})
export class CompetitionModule { }