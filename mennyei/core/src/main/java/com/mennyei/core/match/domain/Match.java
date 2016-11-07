package com.mennyei.core.match.domain;

import java.time.LocalDateTime;
import java.util.Set;

import com.mennyei.core.match.Result;
import com.mennyei.core.match.domain.match.event.MatchEvent;
import com.mennyei.core.team.domain.Team;

import lombok.Data;

@Data
public class Match {
	private LocalDateTime matchDate;
	
	private Result result;
	
	private Team home;
	
	private Team away;
	
	private Set<MatchEvent> events;
	
}
