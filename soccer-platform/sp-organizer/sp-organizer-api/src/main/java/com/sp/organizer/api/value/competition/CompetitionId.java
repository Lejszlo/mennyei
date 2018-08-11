package com.sp.organizer.api.value.competition;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.Value;

@Value
@AllArgsConstructor
public class CompetitionId {
    @NonNull
    private String value;

    public static CompetitionId competitionId(String value) {
        return new CompetitionId(value);
    }

    public String getValue() {
        return value;
    }
}
