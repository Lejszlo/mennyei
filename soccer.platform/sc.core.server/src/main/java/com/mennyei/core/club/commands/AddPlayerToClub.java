package com.mennyei.core.club.commands;

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
public class AddPlayerToClub extends ClubCommand {

	@NonNull
    private String playerId;
    
	@NonNull
    private String clubId;
	
	public static AddPlayerToClubBuilder builder(String clubId, String playerId) {
		return hiddenBuilder().clubId(clubId).playerId(playerId);
	}

}
