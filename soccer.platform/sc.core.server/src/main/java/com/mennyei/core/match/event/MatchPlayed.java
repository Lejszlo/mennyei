package com.mennyei.core.match.event;

import com.mennyei.core.match.domain.MatchResultDetailes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Builder(builderMethodName = "hiddenBuilder")
@Value
@AllArgsConstructor
public class MatchPlayed implements MatchEvents {
	private int fans;
	
	private boolean played;
	
	private MatchResultDetailes matchResultDetailes;
	
	public static MatchPlayedBuilder builder(MatchResultDetailes matchResultDetailes) {
		return hiddenBuilder().matchResultDetailes(matchResultDetailes);
	}
	
}
