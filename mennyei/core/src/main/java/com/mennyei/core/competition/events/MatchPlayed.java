package com.mennyei.core.competition.events;

import java.util.List;

import com.mennyei.core.competition.domain.match.domain.match.event.MatchEvent;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NonNull;
import lombok.Singular;
import lombok.Value;

@Builder(builderMethodName = "hiddenBuilder")
@Value
@AllArgsConstructor
public class MatchPlayed implements CompetitionEvent {
	@NonNull
	private String competitionId;
	
	@NonNull
	private String stageName;
	
	private int turnIndex;
	
	@NonNull
	private String homeClubId;
	
	@Singular
	private List<MatchEvent> homeClubEvents;
	
	@Singular
	private List<MatchEvent> awayClubEvents;
	
	public static MatchPlayedBuilder builder(String competitionId, String stageName, int turnIndex, String homeClubId) {
		return hiddenBuilder().competitionId(competitionId).stageName(stageName).turnIndex(turnIndex).homeClubId(homeClubId);
	}
}
