package com.hajdu.sp.competition.update.event.club;

import com.hajdu.sp.competition.update.event.club.ClubEvent;
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
