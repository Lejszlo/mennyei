package com.mennyei.core.match.domain.event;

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
