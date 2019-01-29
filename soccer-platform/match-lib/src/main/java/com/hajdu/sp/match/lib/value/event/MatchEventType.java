package com.hajdu.sp.match.lib.value.event;

public enum MatchEventType {
	YELLOW_CARD(CardEvent.class),
	RED_CARD(CardEvent.class),
	GOAL(GoalEvent.class),
	OWN_GOAL(GoalEvent.class),
	SUBSTITUTION(SubstitutionGameEvent.class);
	
	private Class<? extends GameEvent> eventClass;

	private MatchEventType(Class<? extends GameEvent> eventClass) {
		this.eventClass = eventClass;
	}
	
	public Class<? extends GameEvent> getEventClass() {
		return eventClass;
	}
}
