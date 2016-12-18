package com.mennyei.core.competition.domain.match.domain.match.event;

public class MatchEvent {
	protected MatchEventType matchEventType;
	
	protected int minute;
	
	public MatchEventType getMatchEventType() {
		return matchEventType;
	}
	
	public int getMinute() {
		return minute;
	}
}
