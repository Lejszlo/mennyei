package com.sp.organizer.backend.match.command;

import java.util.List;

import com.sp.core.backend.value.match.event.MatchEvent;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Singular;
import lombok.Value;

import javax.validation.constraints.NotNull;

@Builder(builderMethodName = "hiddenBuilder")
@Value
@EqualsAndHashCode(callSuper = false)
public class PlayMatchCommand extends MatchCommand {
	
	@Singular
	private List<MatchEvent> homeClubevents;
	
	@Singular
	private List<MatchEvent> awayClubevents;

	@NotNull
	private String competitionId;

	public static PlayMatchCommandBuilder builder(String competitionId) {
		return hiddenBuilder().competitionId(competitionId);
	}
}
