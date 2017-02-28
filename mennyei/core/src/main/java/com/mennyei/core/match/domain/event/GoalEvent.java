package com.mennyei.core.match.domain.event;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@Builder
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
public class GoalEvent extends MatchEvent {
	private String scorerId;

	public static GoalEvent goalOf(String scorerId, int minute) {
		GoalEvent goalEvent = GoalEvent.builder().scorerId(scorerId).build();
		goalEvent.minute = minute;
		goalEvent.matchEventType = MatchEventType.GOAL;
		return goalEvent;
	}
	
	public static GoalEvent ownGoalOf(String scorerId, int minute) {
		GoalEvent goalEvent = GoalEvent.builder().scorerId(scorerId).build();
		goalEvent.minute = minute;
		goalEvent.matchEventType = MatchEventType.GOAL;
		return goalEvent;
	}
}
