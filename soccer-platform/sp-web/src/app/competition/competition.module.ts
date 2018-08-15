import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CompetitionComponent } from './competition/competition.component';
import {CompetitionService} from "app/competition/competition/competition.component.service";
import {
  MatButtonModule,
  MatCardModule,
  MatDividerModule,
  MatGridListModule,
  MatIconModule,
  MatListModule, MatTableModule, MatTabsModule
} from "@angular/material";
import { TurnComponent } from './competition/turn/turn.component';
import { TableComponent } from './competition/table-container/table/table.component';
import { TableContainerComponent } from './competition/table-container/table-container.component';
import {TablesContainerService} from "./competition/table-container/tables.container.service";

@NgModule({
  imports: [
    CommonModule,
    MatCardModule,
    MatButtonModule,
    MatDividerModule,
    MatGridListModule,
    MatIconModule,
    MatTableModule,
    MatTabsModule
  ],
  declarations: [ CompetitionComponent, TurnComponent, TableComponent, TableContainerComponent ],
  providers: [CompetitionService, TablesContainerService]
})
export class CompetitionModule { }
