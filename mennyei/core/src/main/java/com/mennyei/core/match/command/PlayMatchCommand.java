package com.mennyei.core.match.command;

import java.util.List;

import com.mennyei.core.match.domain.event.MatchEvent;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Singular;
import lombok.Value;

@Builder(builderMethodName = "hiddenBuilder")
@Value
@EqualsAndHashCode(callSuper = false)
public class PlayMatchCommand extends MatchCommand {
	@Singular
	private List<MatchEvent> homeClubevents;
	
	@Singular
	private List<MatchEvent> awayClubevents;

	public static PlayMatchCommandBuilder builder() {
		return hiddenBuilder();
	}
}
