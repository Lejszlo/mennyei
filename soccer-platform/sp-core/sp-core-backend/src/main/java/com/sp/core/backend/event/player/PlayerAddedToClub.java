package com.sp.core.backend.event.player;

import com.sp.core.backend.event.organizer.club.ClubEvent;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.Value;

/**
 * Created by lejsz on 2016. 11. 22..
 */
@Value
@AllArgsConstructor
public class PlayerAddedToClub implements ClubEvent {

	@NonNull
	private String clubId;

	@NonNull
    private String playerId;


}
