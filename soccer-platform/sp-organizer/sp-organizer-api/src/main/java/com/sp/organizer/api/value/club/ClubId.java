package com.sp.organizer.api.value.club;

import lombok.NoArgsConstructor;
import org.apache.commons.lang.builder.EqualsBuilder;

@NoArgsConstructor
public class ClubId {
    protected String value;

    protected ClubId(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static ClubId clubId(String value) {
        return new ClubId(value);
    }

    @Override
    public boolean equals(Object that) {
        return EqualsBuilder.reflectionEquals(this, that);
}
}
