import {Component, Input, OnInit} from '@angular/core';
import 'reflect-metadata'

@Component({
    selector: 'sp-club-details',
    templateUrl: './club.details.component.html'
})
export class ClubDetailsComponent {
    @Input() club: any;
}