package sp.match.api.value.event;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import javax.validation.constraints.NotNull;

@JsonTypeInfo(use=JsonTypeInfo.Id.CLASS, include=JsonTypeInfo.As.PROPERTY, property="@class")
public abstract class MatchEvent {

	@NotNull
	protected MatchEventType matchEventType;

	@NotNull
	protected int minute;
	
	public MatchEventType getMatchEventType() {
		return matchEventType;
	}
	
	public int getMinute() {
		return minute;
	}
}
