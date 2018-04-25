package com.sp.organizer.api.value.competition;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CompetitionId {
    private String value;

    private CompetitionId(String value) {
        this.value = value;
    }

    public static CompetitionId competitionId(String value) {
        return new CompetitionId(value);
    }

    public String getValue() {
        return value;
    }
}
