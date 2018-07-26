import { Routes } from '@angular/router';
import { CompetitionComponent } from "./competition/competition.component";
import {TablesContainerComponent} from "./competition/tables/tables.container.component";

export const appRoutes: Routes = [
    { path: 'competition', component: CompetitionComponent },
    { path: 'table',      component: TablesContainerComponent },
    // { path: '',
    //     redirectTo: '/competition',
    //     pathMatch: 'full'
    // }
];
