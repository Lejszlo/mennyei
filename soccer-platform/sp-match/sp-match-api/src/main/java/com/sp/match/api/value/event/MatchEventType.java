package com.sp.match.api.value.event;

public enum MatchEventType {
	YELLOW_CARD(CardEvent.class), RED_CARD(CardEvent.class), GOAL(GoalEvent.class), OWN_GOAL(GoalEvent.class), SUBSTITUTION(SubstitutionEvent.class);
	
	private Class<? extends MatchEvent> eventClass;

	private MatchEventType(Class<? extends MatchEvent> eventClass) {
		this.eventClass = eventClass;
	}
	
	public Class<? extends MatchEvent> getEventClass() {
		return eventClass;
	}
}
