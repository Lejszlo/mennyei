package com.sp.core.query.configurations;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.time.LocalDate;
import java.util.Comparator;

@Data
public final class Interval implements Comparable<Interval> {

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate startDate;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate endDate;

    public static Interval from(LocalDate startDate, LocalDate endDate) {
        Interval interval = new Interval();
        interval.setStartDate(startDate);
        interval.setEndDate(endDate);
        return interval;
    }

    @Override
    public int compareTo(Interval that) {
        return Comparator
                .comparing(Interval::getStartDate)
                .compare(this,that);
    }
}
