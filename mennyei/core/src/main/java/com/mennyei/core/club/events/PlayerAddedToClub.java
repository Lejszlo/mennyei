package com.mennyei.core.club.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

/**
 * Created by lejsz on 2016. 11. 22..
 */
@Builder(builderMethodName="hiddenBuilder")
@Value
@AllArgsConstructor
public class PlayerAddedToClub implements ClubEvent {

	@NonNull
    private String playerId;
    
	@NonNull
    private String clubId;
	
	public static PlayerAddedToClubBuilder builder(String clubId, String playerId) {
		return hiddenBuilder().clubId(clubId).playerId(playerId);
	}

}
