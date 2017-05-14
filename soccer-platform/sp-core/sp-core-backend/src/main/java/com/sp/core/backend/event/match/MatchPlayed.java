package com.sp.core.backend.event.match;

import com.sp.core.backend.value.match.MatchResultDetailes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Builder(builderMethodName = "hiddenBuilder")
@Value
@AllArgsConstructor
public class MatchPlayed implements MatchEvents {
	private int fans;
	
	private boolean played;

	private String competitionId;

	private MatchResultDetailes matchResultDetailes;
	
	public static MatchPlayedBuilder builder(MatchResultDetailes matchResultDetailes, String competitionId) {
		return hiddenBuilder().matchResultDetailes(matchResultDetailes).competitionId(competitionId);
	}
	
}
