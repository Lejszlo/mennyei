import {Component, Input, OnInit} from '@angular/core';
import 'reflect-metadata'
import {MatchResource} from "../competition.types";

@Component({
    selector: 'sp-match',
    templateUrl: './match.component.html'
})
export class MatchComponent implements OnInit {
    @Input()
    match: MatchResource;

    ngOnInit(): void {
    }

}