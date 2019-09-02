package com.hajdu.sp.competition.update.util;

import lombok.NonNull;
import lombok.Value;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Value
public class SpDateTime {

    @NonNull
    String date;

    public static SpDateTime spDateTime(LocalDateTime localDateTime) {
        return new SpDateTime(localDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
    }

}
