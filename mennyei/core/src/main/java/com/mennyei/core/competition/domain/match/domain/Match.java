package com.mennyei.core.competition.domain.match.domain;

import java.util.Set;

import com.mennyei.core.competition.domain.match.domain.match.event.MatchEvent;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder(builderMethodName="hiddenBuilder")
@AllArgsConstructor
public class Match {
	@NonNull
	private String matchDate;
	
	private boolean played;
	
	private Result result;
	
	@NonNull
	private String homeClubId;
	
	@NonNull
	private String awayClubId;
	
	private Set<MatchEvent> events;
	
	public static MatchBuilder builder(String homeClubId, String awayClubId, String matchDate) {
		return hiddenBuilder().homeClubId(homeClubId).awayClubId(awayClubId).matchDate(matchDate);
	}
}
