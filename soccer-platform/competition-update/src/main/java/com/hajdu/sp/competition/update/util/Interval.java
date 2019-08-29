package com.hajdu.sp.competition.update.util;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
public final class Interval implements Comparable<Interval> {

    private String startDate;

    private String endDate;

    public static Interval from(LocalDateTime startDate, LocalDateTime endDate) {
        return new Interval(startDate.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME), endDate.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
    }

    public boolean getContainsPredicate(LocalDateTime date) {
        return LocalDateTime.parse(endDate).isBefore(date) && LocalDateTime.parse(startDate).isAfter(date);
    }

    @Override
    public int compareTo(Interval that) {
        return LocalDateTime.parse(startDate).compareTo(LocalDateTime.parse((that.startDate)));
    }

}
