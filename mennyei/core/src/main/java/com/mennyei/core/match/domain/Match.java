package com.mennyei.core.match.domain;

import com.mennyei.core.match.domain.match.event.MatchEvent;
import com.mennyei.core.team.domain.Club;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
public class Match {
	private LocalDateTime matchDate;
	
	private Result result;
	
	private Club home;
	
	private Club away;
	
	private Set<MatchEvent> events;
	
}
