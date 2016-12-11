package com.mennyei.core.match.domain;

import java.util.Set;

import com.mennyei.core.match.domain.match.event.MatchEvent;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
public class Match {
	private String matchDate;
	
	private boolean played;
	
	private Result result;
	
	private String homeClubId;
	
	private String awayClubId;
	
	private Set<MatchEvent> events;
}
