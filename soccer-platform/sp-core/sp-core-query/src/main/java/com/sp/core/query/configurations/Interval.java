package com.sp.core.query.configurations;

import lombok.Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;

@Data
public final class Interval implements Comparable<Interval> {

    private String startDate;

    private String endDate;

    public static Interval from(LocalDate startDate, LocalDate endDate) {
        Interval interval = new Interval();
        interval.setStartDate(startDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        interval.setEndDate(endDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        return interval;
    }

    @Override
    public int compareTo(Interval that) {
        return Comparator
                .comparing(Interval::getStartDate)
                .compare(this,that);
    }
}
