import {Component, Input} from '@angular/core';
import 'reflect-metadata'
import {Club} from "../../competition/competition.types";

@Component({
    selector: 'sp-club-details',
    templateUrl: './club.details.component.html'
})
export class ClubDetailsComponent {
    @Input() club: Club;
}