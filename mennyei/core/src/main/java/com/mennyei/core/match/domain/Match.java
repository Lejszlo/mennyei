package com.mennyei.core.match.domain;

import java.time.LocalDateTime;
import java.util.Set;

import com.mennyei.core.match.domain.match.event.MatchEvent;
import com.mennyei.core.team.domain.Club;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Match {
	private LocalDateTime matchDate;
	
	private Result result;
	
	private Club home;
	
	private Club away;
	
	private Set<MatchEvent> events;
	
}
