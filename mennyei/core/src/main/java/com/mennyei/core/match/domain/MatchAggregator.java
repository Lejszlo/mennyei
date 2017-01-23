package com.mennyei.core.match.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.mennyei.core.match.command.AddMatchCommand;
import com.mennyei.core.match.command.MatchCommand;
import com.mennyei.core.match.command.PlayMatchCommand;
import com.mennyei.core.match.domain.event.MatchEvent;
import com.mennyei.core.match.domain.event.MatchEventType;
import com.mennyei.core.match.domain.event.lineup.LineUp;
import com.mennyei.core.match.event.MatchAdded;
import com.mennyei.core.match.event.MatchPlayed;
import com.mennyei.core.match.event.MatchSet;

import io.eventuate.Event;
import io.eventuate.ReflectiveMutableCommandProcessingAggregate;

public class MatchAggregator extends ReflectiveMutableCommandProcessingAggregate<MatchAggregator, MatchCommand> {
	private MatchInfo matchInfo;

	private List<LineUp> homeLineUps = new ArrayList<>();

	private List<LineUp> awayLineUps = new ArrayList<>();

	private List<MatchEvent> homeClubevents = new ArrayList<>();

	private List<MatchEvent> awayClubevents = new ArrayList<>();

	public MatchInfo getMatchInfo() {
		return matchInfo;
	}

	public List<Event> process(AddMatchCommand addMatchCommand) {
		return Arrays.asList(MatchAdded.builder(addMatchCommand.getMatchInfo()).build());
	}

	public List<Event> process(PlayMatchCommand fillMatchCommand) {
		return Arrays.asList(MatchPlayed.builder()
				.homeClubEvents(fillMatchCommand.getHomeClubevents())
				.awayClubEvents(fillMatchCommand.getAwayClubevents())
				.matchInfo(matchInfo)
				.build());
	}

	public void apply(MatchAdded matchAdded) {
		matchInfo = matchAdded.getMatchInfo();
	}

	public void apply(MatchSet matchSet) {
		homeLineUps.addAll(matchSet.getHomeLineUps());
		awayLineUps.addAll(matchSet.getAwayLineUps());
	}

	public void apply(MatchPlayed matchPlayed) {
		homeClubevents.addAll(matchPlayed.getHomeClubEvents());
		awayClubevents.addAll(matchPlayed.getAwayClubEvents());
		calculateResult();
		matchInfo.setPlayed(true);
	}

	public void calculateResult() {
		matchInfo.setResultGoals(ResultGoals.builder(getHomeGoalAmount() + getAwayOwnGoalAmount(), getAwayGoalAmount() + getHomeOwnGoalAmount()).build());
	}

	private int getHomeGoalAmount() {
		return filterEvents(homeClubevents, new HashSet<>(Arrays.asList(MatchEventType.GOAL))).size();
	}

	private int getHomeOwnGoalAmount() {
		return filterEvents(homeClubevents, new HashSet<>(Arrays.asList(MatchEventType.OWN_GOAL))).size();
	}

	private int getAwayGoalAmount() {
		return filterEvents(awayClubevents, new HashSet<>(Arrays.asList(MatchEventType.GOAL))).size();
	}

	private int getAwayOwnGoalAmount() {
		return filterEvents(awayClubevents, new HashSet<>(Arrays.asList(MatchEventType.OWN_GOAL))).size();
	}

	public int calculateTotalYellowCardAmount() {
		List<MatchEvent> events = new ArrayList<>(awayClubevents);
		events.addAll(homeClubevents);
		return filterEvents(events, new HashSet<>(Arrays.asList(MatchEventType.YELLOW_CARD))).size();
	}

	public int calculateTotalRedCardAmount() {
		List<MatchEvent> events = new ArrayList<>(awayClubevents);
		events.addAll(homeClubevents);
		return filterEvents(events, new HashSet<>(Arrays.asList(MatchEventType.RED_CARD))).size();
	}

	public List<MatchEvent> filterEvents(List<MatchEvent> events, Set<MatchEventType> eventTypes) {
		return events.stream().filter(e -> eventTypes.contains(e.getMatchEventType())).collect(Collectors.toList());
	}
}
