import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';
import { AppComponent } from './app.component';
import { NavBarComponent } from "./navbar/nav.component";
import { CompetitionModule } from "./competition/competition.module";
import { appRoutes } from "./app.routes";

@NgModule({
    imports: [
        BrowserModule,
        CompetitionModule,
        RouterModule.forRoot(appRoutes)
    ],
    declarations: [
        AppComponent,
        NavBarComponent
    ],
    bootstrap: [ AppComponent, NavBarComponent ]
})
export class AppModule { }