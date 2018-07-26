package com.sp.core.query.configurations;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
public final class Interval implements Comparable<Interval> {

    private String startDate;

    private String endDate;

    public static Interval from(LocalDate startDate, LocalDate endDate) {
        return new Interval(
                startDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                endDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        );
    }

    @Override
    public int compareTo(Interval that) {
        return startDate.compareTo(that.startDate);
    }
}
