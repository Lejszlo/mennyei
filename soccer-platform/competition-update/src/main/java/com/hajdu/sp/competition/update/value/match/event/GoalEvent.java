package com.hajdu.sp.competition.update.value.match.event;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@Builder
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
public class GoalEvent extends GameEvent {
	private String scorerId;

	public static GoalEvent goalOf(int minute) {
		return goalOf(null, minute);
	}

	public static GoalEvent ownGoalOf(int minute) {
		return ownGoalOf(null, minute);
	}

	public static GoalEvent goalOf(String scorerId, int minute) {
		GoalEvent goalEvent = GoalEvent.builder().scorerId(scorerId).build();
		goalEvent.setMinute(minute);
		goalEvent.setMatchEventType(MatchEventType.GOAL);
		return goalEvent;
	}
	
	public static GoalEvent ownGoalOf(String scorerId, int minute) {
		GoalEvent goalEvent = GoalEvent.builder().scorerId(scorerId).build();
		goalEvent.setMinute(minute);
		goalEvent.setMatchEventType(MatchEventType.OWN_GOAL);
		return goalEvent;
	}
}
