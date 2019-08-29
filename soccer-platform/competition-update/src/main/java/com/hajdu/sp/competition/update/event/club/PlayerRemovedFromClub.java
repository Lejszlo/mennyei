package com.hajdu.sp.competition.update.event.club;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
@AllArgsConstructor
public class PlayerRemovedFromClub implements ClubEvent {

    private String playerId;

}
