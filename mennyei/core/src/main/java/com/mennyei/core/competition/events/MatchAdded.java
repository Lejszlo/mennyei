package com.mennyei.core.competition.events;

import java.util.List;

import com.mennyei.core.competition.domain.match.domain.Match;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NonNull;
import lombok.Singular;
import lombok.Value;

@Builder(builderMethodName="hiddenBuilder")
@Value
@AllArgsConstructor
public class MatchAdded implements CompetitionEvent {
	@NonNull
	private String competitionId;
	
	@NonNull
	private String stageName; 
	
	private int turnIndex; 
	
	@Singular
	private List<Match> matches;
	
	public static MatchAddedBuilder builder(String competitionId, String stageName, int turnIndex) {
		return hiddenBuilder().competitionId(competitionId).stageName(stageName).turnIndex(turnIndex);
	}
}
