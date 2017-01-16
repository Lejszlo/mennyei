package com.mennyei.core.match.event;

import java.util.List;

import com.mennyei.core.match.domain.MatchInfo;
import com.mennyei.core.match.domain.event.MatchEvent;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Singular;
import lombok.Value;

@Builder(builderMethodName = "hiddenBuilder")
@Value
@AllArgsConstructor
public class MatchPlayed implements MatchEvents {
	private MatchInfo matchInfo;
	
	@Singular
	private List<MatchEvent> homeClubEvents;
	
	@Singular
	private List<MatchEvent> awayClubEvents;
	
	public static MatchPlayedBuilder builder() {
		return hiddenBuilder();
	}
}
