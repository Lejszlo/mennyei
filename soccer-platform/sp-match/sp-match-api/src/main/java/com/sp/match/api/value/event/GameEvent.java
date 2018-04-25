package com.sp.match.api.value.event;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@JsonTypeInfo(use=JsonTypeInfo.Id.CLASS, include=JsonTypeInfo.As.PROPERTY, property="@class")
@Setter
@Getter
public abstract class GameEvent {

	@NotNull
	private MatchEventType matchEventType;

	@NotNull
	private int minute;
	
	public MatchEventType getMatchEventType() {
		return matchEventType;
	}
	
	public int getMinute() {
		return minute;
	}
}
