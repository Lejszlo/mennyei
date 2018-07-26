import {Component, Input} from '@angular/core';
import 'reflect-metadata'
import {Row} from "../../competition.types";

@Component({
    selector: 'sp-table',
    templateUrl: './table.component.html'
})
export class TableComponent {
    @Input()
    rows: Row[];

    constructor () {
    }

}