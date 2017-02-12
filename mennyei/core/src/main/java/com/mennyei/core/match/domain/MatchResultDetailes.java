package com.mennyei.core.match.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.mennyei.core.match.domain.event.MatchEvent;
import com.mennyei.core.match.domain.event.MatchEventType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Singular;

@Builder(builderMethodName = "hiddenBuilder")
@AllArgsConstructor
public class MatchResultDetailes {

	@Getter
	private int homeGoalAmount;

	@Getter
	private int awayGoalAmount;
	
	@Singular
	private List<MatchEvent> homeClubEvents;
	
	@Singular
	private List<MatchEvent> awayClubEvents;
	
	public MatchResultDetailes calculateResult() {
		homeGoalAmount = getHomeGoalAmountFromEvent() + getAwayOwnGoalAmountFromEvent();
		awayGoalAmount = getAwayGoalAmountFromEvent() + getHomeOwnGoalAmountFromEvent();
		return this;
	}
	
	public int getHomeGoalAmountFromEvent() {
		return filterEvents(homeClubEvents, Arrays.asList(MatchEventType.GOAL)).size();
	}

	public int getHomeOwnGoalAmountFromEvent() {
		return filterEvents(homeClubEvents, Arrays.asList(MatchEventType.OWN_GOAL)).size();
	}

	public int getAwayGoalAmountFromEvent() {
		return filterEvents(awayClubEvents, Arrays.asList(MatchEventType.GOAL)).size();
	}

	public int getAwayOwnGoalAmountFromEvent() {
		return filterEvents(awayClubEvents, Arrays.asList(MatchEventType.OWN_GOAL)).size();
	}
	
	public List<MatchEvent> getHomeEvents(List<MatchEventType> eventTypes) {
		return filterEvents(homeClubEvents, eventTypes);
	}
	
	public List<MatchEvent> getAwayEvents(List<MatchEventType> eventTypes) {
		return filterEvents(awayClubEvents, eventTypes);
	}
	
	public List<MatchEvent> getEvents(List<MatchEventType> eventTypes) {
		List<MatchEvent> events = new ArrayList<>();
		events.addAll(getHomeEvents(eventTypes));
		events.addAll(getAwayEvents(eventTypes));
		return Collections.unmodifiableList(events);
	}
	
	
	private List<MatchEvent> filterEvents(List<MatchEvent> events, List<MatchEventType> eventTypes) {
		return Collections.unmodifiableList(events.stream().filter(e -> eventTypes.contains(e.getMatchEventType())).collect(Collectors.toList()));
	}

	public WinnerType whoIsTheWinner() {
		int result = homeGoalAmount - awayGoalAmount;
		if (result > 0) {
			return WinnerType.HOME;
		}
		if (result < 0) {
			return WinnerType.AWAY;
		}
		return WinnerType.DRAW;
	}

	public static MatchResultDetailesBuilder builder(List<MatchEvent> homeClubEvents, List<MatchEvent> awayClubEvents) {
		return hiddenBuilder().awayClubEvents(awayClubEvents).homeClubEvents(homeClubEvents);
	}

}
