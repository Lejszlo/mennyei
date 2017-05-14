package com.sp.organizer.backend.competition.events;

import com.sp.organizer.backend.competition.domain.season.Turn;

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
