package com.sp.organizer.api.value.club;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AwayClubId extends ClubId {

    private AwayClubId(String value) {
        super(value);
    }

    public static AwayClubId awayClubId(String value) {
        return new AwayClubId(value);
    }
}
