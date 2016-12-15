package com.mennyei.core.competition.commands;

import java.util.Set;

import com.mennyei.core.competition.domain.match.domain.match.event.MatchEvent;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.Singular;
import lombok.Value;

@Builder(builderMethodName = "hiddenBuilder")
@Value
@EqualsAndHashCode(callSuper = false)
public class PlayMatchCommand extends CompetitionCommand {
	@NonNull
	private String competitionId;
	
	@NonNull
	private String stageName;
	
	private int turnIndex;
	
	@NonNull
	private String homeClubId;
	
	@Singular
	private Set<MatchEvent> events;

	public static PlayMatchCommandBuilder builder(String competitionId, String stageName, int turnIndex, String homeClubId) {
		return hiddenBuilder().competitionId(competitionId).stageName(stageName).turnIndex(turnIndex).homeClubId(homeClubId);
	}
}
