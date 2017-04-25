package com.mennyei.core.competition.events;

import com.mennyei.core.competition.domain.season.Turn;
import com.mennyei.core.competition.events.CompetitionEvent;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Builder(builderMethodName="hiddenBuilder")
@Value
@AllArgsConstructor
public class MatchAdded implements CompetitionEvent {
	@NonNull
	private String competitionId;
	
	@NonNull
	private String stageName; 
	
	private Turn turn; 
	
	public static MatchAddedBuilder builder(String competitionId, String stageName, Turn turn) {
		return hiddenBuilder().competitionId(competitionId).stageName(stageName).turn(turn);
	}
}
