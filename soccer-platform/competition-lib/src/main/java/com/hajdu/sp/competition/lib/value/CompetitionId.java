package com.hajdu.sp.competition.lib.value;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.Value;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompetitionId that = (CompetitionId) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
