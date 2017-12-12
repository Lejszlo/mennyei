import { Routes } from '@angular/router';
import { CompetitionComponent } from "./competition/dashboard/competition.component";
import {TableComponent} from "./competition/table/table.component";

export const appRoutes: Routes = [
    { path: 'competition', component: CompetitionComponent },
    { path: 'table',      component: TableComponent },
    // { path: '',
    //     redirectTo: '/competition',
    //     pathMatch: 'full'
    // }
];
