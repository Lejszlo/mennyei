package com.hajdu.sp.club.lib.value;

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
