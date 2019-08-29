package com.hajdu.sp.competition.update.event.club;

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
