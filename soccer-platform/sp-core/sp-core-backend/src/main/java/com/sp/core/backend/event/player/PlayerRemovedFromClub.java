package com.sp.core.backend.event.player;

import com.sp.core.backend.event.organizer.club.ClubEvent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

/**
 * Created by lejsz on 2016. 11. 22..
 */
@Builder
@Value
@AllArgsConstructor
public class PlayerRemovedFromClub implements ClubEvent {

    private String playerId;

}
