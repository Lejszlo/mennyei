package com.hajdu.sp.competition.update.value.club;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ClubId {
    private String id;

    protected ClubId(String id) {
        this.id = id;
    }

    public static ClubId clubId(String id) {
        return new ClubId(id);
    }
}
