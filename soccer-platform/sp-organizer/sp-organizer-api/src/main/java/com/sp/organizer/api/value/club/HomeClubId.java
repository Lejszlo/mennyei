package com.sp.organizer.api.value.club;

import com.sp.organizer.api.value.competition.ClubId;
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
