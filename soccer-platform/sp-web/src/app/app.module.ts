import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import {MatButtonModule, MatCardModule, MatMenuModule, MatToolbarModule} from "@angular/material";
import { NavbarComponent } from "./navbar/navbar.component";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { RouterModule, Routes } from "@angular/router";
import { CompetitionComponent } from "./competition/competition/competition.component";
import { CompetitionModule } from "./competition/competition.module";
import { HttpClientModule } from "@angular/common/http";

const routes: Routes = [
  { path: 'competition', component: CompetitionComponent }
];

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent
  ],
  imports: [
    HttpClientModule,
    BrowserModule,
    MatToolbarModule,
    MatMenuModule,
    MatButtonModule,
    BrowserAnimationsModule,
    RouterModule.forRoot(routes),
    CompetitionModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
