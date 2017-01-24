package com.mennyei.core.match.domain;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
	
	private int getHomeGoalAmountFromEvent() {
		return filterEvents(homeClubEvents, new HashSet<>(Arrays.asList(MatchEventType.GOAL))).size();
	}

	private int getHomeOwnGoalAmountFromEvent() {
		return filterEvents(homeClubEvents, new HashSet<>(Arrays.asList(MatchEventType.OWN_GOAL))).size();
	}

	private int getAwayGoalAmountFromEvent() {
		return filterEvents(awayClubEvents, new HashSet<>(Arrays.asList(MatchEventType.GOAL))).size();
	}

	private int getAwayOwnGoalAmountFromEvent() {
		return filterEvents(awayClubEvents, new HashSet<>(Arrays.asList(MatchEventType.OWN_GOAL))).size();
	}

	public List<MatchEvent> filterEvents(List<MatchEvent> events, Set<MatchEventType> eventTypes) {
		return events.stream().filter(e -> eventTypes.contains(e.getMatchEventType())).collect(Collectors.toList());
	}

	public MatchResultType whoIsTheWinner() {
		int result = homeGoalAmount - awayGoalAmount;
		if (result > 0) {
			return MatchResultType.HOME;
		}
		if (result < 0) {
			return MatchResultType.AWAY;
		}
		return MatchResultType.DRAW;
	}

	public static MatchResultDetailesBuilder builder(List<MatchEvent> homeClubEvents, List<MatchEvent> awayClubEvents) {
		return hiddenBuilder().awayClubEvents(awayClubEvents).homeClubEvents(homeClubEvents);
	}

}
