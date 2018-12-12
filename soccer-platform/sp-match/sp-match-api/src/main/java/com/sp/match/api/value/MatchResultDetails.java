package com.sp.match.api.value;

import com.sp.match.api.value.event.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Builder(builderMethodName = "hiddenBuilder", buildMethodName = "hiddenBuild")
@AllArgsConstructor
@Data
public class MatchResultDetails {

	@Getter
	private int homeGoalAmount;

	@Getter
	private int awayGoalAmount;

    @Getter
    private int homeYellowCardAmount;

    @Getter
    private int awayYellowCardAmount;

    @Getter
    private int homeRedCardAmount;

    @Getter
    private int awayRedCardAmount;

	@Getter
	private WinnerType winner;

	@Singular
	private List<GameEvent> homeClubEvents;

	@Singular
	private List<GameEvent> awayClubEvents;

	private MatchResultDetails calculateResult() {
		homeGoalAmount = getHomeGoalAmountFromEvent() + getAwayOwnGoalAmountFromEvent();
		awayGoalAmount = getAwayGoalAmountFromEvent() + getHomeOwnGoalAmountFromEvent();
		homeYellowCardAmount = getHomeYellowCardAmountFromEvent();
        awayYellowCardAmount = getAwayYellowCardAmountFromEvent();
        homeRedCardAmount = getHomeRedCardAmountFromEvent();
        awayRedCardAmount = getAwayRedCardAmountFromEvent();
		winner = whoIsTheWinner();
		return this;
	}

	private int getHomeGoalAmountFromEvent() {
		return filterEvents(homeClubEvents, MatchEventType.GOAL).size();
	}

	private int getHomeOwnGoalAmountFromEvent() {
		return filterEvents(homeClubEvents, MatchEventType.OWN_GOAL).size();
	}

	private int getAwayGoalAmountFromEvent() {
		return filterEvents(awayClubEvents, MatchEventType.GOAL).size();
	}

	private int getAwayOwnGoalAmountFromEvent() {
		return filterEvents(awayClubEvents, MatchEventType.OWN_GOAL).size();
	}

    private int getAwayYellowCardAmountFromEvent() {
        return filterEvents(awayClubEvents, MatchEventType.YELLOW_CARD).size();
    }

    private int getAwayRedCardAmountFromEvent() {
        return filterEvents(awayClubEvents, MatchEventType.RED_CARD).size();
    }

    private int getHomeYellowCardAmountFromEvent() {
        return filterEvents(homeClubEvents, MatchEventType.YELLOW_CARD).size();
    }

    private int getHomeRedCardAmountFromEvent() {
        return filterEvents(homeClubEvents, MatchEventType.RED_CARD).size();
    }

	private <T extends GameEvent> List<T> filterHomeEvents(Class<T> eventClass, MatchEventType... eventTypes) {
		return filterEvents(homeClubEvents, eventTypes).stream().filter(eventClass::isInstance).map(eventClass::cast)
				.collect(Collectors.toList());
	}

	private <T extends GameEvent> List<T> filterAwayEvents(Class<T> eventClass, MatchEventType... eventTypes) {
		return filterEvents(awayClubEvents, eventTypes).stream().filter(eventClass::isInstance).map(eventClass::cast)
				.collect(Collectors.toList());
	}

	public List<GoalEvent> getGoalEventsForPlayer(String playerId) {
		return filterEvents(GoalEvent.class, MatchEventType.GOAL, MatchEventType.OWN_GOAL).stream()
				.filter(event -> event.getScorerId().equals(playerId)).collect(Collectors.toList());
	}
	
	public List<GoalEvent> getGoalEventsForPlayer(String playerId, MatchEventType matchEventType) {
		return filterEvents(GoalEvent.class, matchEventType).stream()
				.filter(event -> event.getScorerId().equals(playerId)).collect(Collectors.toList());
	}

	public List<CardEvent> getCardEventsForPlayer(String playerId) {
		return filterEvents(CardEvent.class, MatchEventType.YELLOW_CARD, MatchEventType.RED_CARD).stream()
				.filter(event -> event.getSuffererId().equals(playerId)).collect(Collectors.toList());
	}
	
	public List<CardEvent> getCardEventsForPlayer(String playerId, MatchEventType matchEventType) {
		return filterEvents(CardEvent.class, matchEventType).stream()
				.filter(event -> event.getSuffererId().equals(playerId)).collect(Collectors.toList());
	}
	
	public List<SubstitutionGameEvent> getSubstitutionEventForPlayer(String playerId) {
		return filterEvents(SubstitutionGameEvent.class, MatchEventType.SUBSTITUTION).stream()
				.filter(event -> event.getInner().equals(playerId) || event.getOuter().equals(playerId)).collect(Collectors.toList());
	}

	public List<SubstitutionGameEvent> getSubstitutionInEventForPlayer(String playerId) {
		return filterEvents(SubstitutionGameEvent.class, MatchEventType.SUBSTITUTION).stream()
				.filter(event -> event.getInner().equals(playerId)).collect(Collectors.toList());
	}
	
	public List<SubstitutionGameEvent> getSubstitutionOutEventForPlayer(String playerId) {
		return filterEvents(SubstitutionGameEvent.class, MatchEventType.SUBSTITUTION).stream()
				.filter(event -> event.getOuter().equals(playerId)).collect(Collectors.toList());
	}

	public <T extends GameEvent> List<T> filterEvents(Class<T> eventClass, MatchEventType... eventTypes) {
		List<T> events = new ArrayList<>();
		events.addAll(filterHomeEvents(eventClass, eventTypes));
		events.addAll(filterAwayEvents(eventClass, eventTypes));
		return Collections.unmodifiableList(events);
	}

	private List<GameEvent> filterEvents(List<GameEvent> events, MatchEventType... eventTypes) {
		List<MatchEventType> eventTypeList = Arrays.asList(eventTypes);
		if (events == null) {
			return Collections.emptyList();
		}
		return events.stream().filter(e -> eventTypeList.contains(e.getMatchEventType())).collect(Collectors.toList());
	}

	private WinnerType whoIsTheWinner() {
		int result = homeGoalAmount - awayGoalAmount;
		if (result > 0) {
			return WinnerType.HOME;
		}
		if (result < 0) {
			return WinnerType.AWAY;
		}
		return WinnerType.DRAW;
	}

	public static MatchResultDetails build(List<GameEvent> homeClubEvents, List<GameEvent> awayClubEvents) {
		return hiddenBuilder().awayClubEvents(awayClubEvents).homeClubEvents(homeClubEvents).hiddenBuild().calculateResult();
	}

}
