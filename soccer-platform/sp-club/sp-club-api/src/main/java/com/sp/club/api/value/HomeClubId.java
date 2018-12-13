package com.sp.club.api.value;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class HomeClubId extends ClubId {

    private HomeClubId(String value) {
        super(value);
    }

    public static HomeClubId homeClubId(String value) {
        return new HomeClubId(value);
    }
}
