package com.mennyei.core.competition.domain.match.domain.match.event.goal;

import com.mennyei.core.competition.domain.match.domain.match.event.MatchEvent;
import com.mennyei.core.competition.domain.match.domain.match.event.MatchEventType;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@Builder
@EqualsAndHashCode(callSuper=false)
public class GoalEvent extends MatchEvent {
	private String scorerId;

	private GoalEventType type;
	
	public static GoalEvent goalOf(String scorerId, int minute) {
		GoalEvent goalEvent = GoalEvent.builder().scorerId(scorerId).type(GoalEventType.GOAL).build();
		goalEvent.minute = minute;
		goalEvent.matchEventType = MatchEventType.GOAL;
		return goalEvent;
	}
	
	public static GoalEvent ownGoalOf(String scorerId, int minute) {
		GoalEvent goalEvent = GoalEvent.builder().scorerId(scorerId).type(GoalEventType.OWNGOAL).build();
		goalEvent.minute = minute;
		goalEvent.matchEventType = MatchEventType.GOAL;
		return goalEvent;
	}
}
