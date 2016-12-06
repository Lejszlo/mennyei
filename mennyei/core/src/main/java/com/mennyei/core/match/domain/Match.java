package com.mennyei.core.match.domain;

import com.mennyei.core.club.domain.value.ClubInfo;
import com.mennyei.core.match.domain.match.event.MatchEvent;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
public class Match {
	private LocalDateTime matchDate;
	
	private Result result;
	
	private ClubInfo home;
	
	private ClubInfo away;
	
	private Set<MatchEvent> events;
	
}
