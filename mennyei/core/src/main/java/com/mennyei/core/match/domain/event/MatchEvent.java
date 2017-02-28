package com.mennyei.core.match.domain.event;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use=JsonTypeInfo.Id.CLASS, include=JsonTypeInfo.As.PROPERTY, property="@class")
public abstract class MatchEvent {
	protected MatchEventType matchEventType;
	
	protected int minute;
	
	public MatchEventType getMatchEventType() {
		return matchEventType;
	}
	
	public int getMinute() {
		return minute;
	}
}
