package com.mennyei.core.competition.events;

import java.util.List;

import com.mennyei.core.competition.domain.match.domain.Match;
import com.mennyei.core.competition.domain.season.Turn;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Builder(builderMethodName="hiddenBuilder")
@Value
@AllArgsConstructor
public class MatchAdded implements CompetitionEvent {
	private String competitionId;
	private String stageName; 
	private Turn turn; 
	private List<Match> matches;
	
	public static MatchAddedBuilder builder(String competitionId, String stageName, Turn turn) {
		return hiddenBuilder().competitionId(competitionId).stageName(stageName).turn(turn);
	}
}
