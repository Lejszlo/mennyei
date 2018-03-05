import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
    name: 'firstLetter'
})
export class FirstLetterPipe implements PipeTransform {

    transform(value:any) {
        if (value) {
            return value.charAt(0).toUpperCase();
        }
        return value;
    }
}