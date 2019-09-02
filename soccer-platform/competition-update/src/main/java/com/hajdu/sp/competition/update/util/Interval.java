package com.hajdu.sp.competition.update.util;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

import static com.hajdu.sp.competition.update.util.SpDateTime.spDateTime;

@Data
@AllArgsConstructor
public final class Interval implements Comparable<Interval> {

    private SpDateTime startDateTime;

    private SpDateTime endDateTime;

    public static Interval from(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return new Interval(spDateTime(startDateTime), spDateTime(endDateTime));
    }

    public boolean getContainsPredicate(LocalDateTime date) {
        return LocalDateTime.parse(endDateTime.getDate()).isBefore(date) && LocalDateTime.parse(startDateTime.getDate()).isAfter(date);
    }

    @Override
    public int compareTo(Interval that) {
        return LocalDateTime.parse(startDateTime.getDate()).compareTo(LocalDateTime.parse((that.startDateTime.getDate())));
    }

}
