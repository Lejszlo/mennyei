package com.mennyei.core.competition.domain.match.domain.match.event;

import lombok.Data;

@Data
public abstract class MatchEvent {
	private MatchEventType matchEventType;
	
}
