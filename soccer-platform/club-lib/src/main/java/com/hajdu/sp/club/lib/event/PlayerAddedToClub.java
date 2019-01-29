package com.hajdu.sp.club.lib.event;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.Value;

@Value
@AllArgsConstructor
public class PlayerAddedToClub implements ClubEvent {

	@NonNull
	private String clubId;

	@NonNull
    private String playerId;


}
