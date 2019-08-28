package com.hajdu.sp.competition.update.util;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public final class Interval implements Comparable<Interval> {

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    public static Interval from(LocalDateTime startDate, LocalDateTime endDate) {
        return new Interval(startDate, endDate);
    }

    public boolean getContainsPredicate(LocalDateTime date) {
        return endDate.isBefore(date) && startDate.isAfter(date);
    }

    @Override
    public int compareTo(Interval that) {
        return startDate.compareTo(that.startDate);
    }

}
