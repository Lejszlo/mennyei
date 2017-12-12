import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common'
import { CompetitionService } from './competition.service';
import { HttpModule }    from '@angular/http';
import { TableComponent } from './table/table.component';
import { CompetitionComponent } from './dashboard/competition.component';
import { ClubDetailsComponent } from '../club/details/club.details.component';

@NgModule({
    imports: [
        HttpModule,
        CommonModule
    ],
    declarations: [
        TableComponent,
        CompetitionComponent,
        ClubDetailsComponent
    ],
    providers: [
        CompetitionService
    ]
})
export class CompetitionModule { }