package com.hajdu.sp.match.lib.value;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.Value;

import java.util.Objects;

@Value
@AllArgsConstructor
public class MatchId {
    @NonNull
    private String value;

    public static MatchId matchId(String value) {
        return new MatchId(value);
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MatchId that = (MatchId) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
