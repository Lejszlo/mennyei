package com.mennyei.core.competition.domain.match.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.mennyei.core.competition.domain.match.domain.match.event.MatchEvent;
import com.mennyei.core.competition.domain.match.domain.match.event.MatchEventType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.Singular;

@Data
@Builder(builderMethodName = "hiddenBuilder")
@AllArgsConstructor
public class Match {
	@NonNull
	private String matchDate;

	private boolean played;
	
	private int fans;

	private Result result;

	@NonNull
	private String homeClubId;

	@NonNull
	private String awayClubId;

	@Singular
	private List<MatchEvent> homeClubevents = new ArrayList<>();

	@Singular
	private List<MatchEvent> awayClubevents = new ArrayList<>();
	
	public static MatchBuilder builder(String homeClubId, String awayClubId, String matchDate) {
		return hiddenBuilder().homeClubId(homeClubId).awayClubId(awayClubId).matchDate(matchDate);
	}

	public boolean containsClub(String clubId) {
		return clubId.equals(homeClubId) || awayClubId.equals(clubId);
	}

	public String whoIsTheOpponentOf(String clubId) {
		if (clubId.equals(homeClubId)) {
			return awayClubId;
		}
		return homeClubId;
	}

	public String whoIsMe(String clubId) {
		if (clubId.equals(homeClubId)) {
			return homeClubId;
		}
		return awayClubId;
	}

	public int getGoalAmountFor(String clubId) {
		if (homeClubId.equals(clubId)) {
			return result.getHomeGoalAmount();
		}
		return result.getAwayGoalAmount();
	}

	public MatchResult getResultFor(String clubId) {
		MatchResultType whoIsTheWinner = result.whoIsTheWinner();
		if (MatchResultType.DRAW.equals(whoIsTheWinner)) {
			return MatchResult.DRAW;
		}
		if (MatchResultType.HOME.equals(whoIsTheWinner) && isAtHome(clubId) || MatchResultType.AWAY.equals(whoIsTheWinner) && !isAtHome(clubId)) {
			return MatchResult.WIN;
		}
		return MatchResult.LOSE;
	}

	public boolean isAtHome(String clubId) {
		return clubId.equals(homeClubId);
	}

	public void calculateResult() {
		result = Result.builder(getHomeGoalAmount() + getAwayOwnGoalAmount(), getAwayGoalAmount() + getHomeOwnGoalAmount()).build();
	}

	public int getScoredGoalAmount(String clubId) throws MatchHasNotPlayedYetException {
		if(result == null) {
			throw new MatchHasNotPlayedYetException();
		}
		
		if(homeClubId.equals(clubId)) {
			return result.getHomeGoalAmount();
		}
		return result.getAwayGoalAmount();
	}

	public int getConcernedGoalAmount(String clubId) throws MatchHasNotPlayedYetException {
		if(result == null) {
			throw new MatchHasNotPlayedYetException();
		}
		
		if(awayClubId.equals(clubId)) {
			return result.getAwayGoalAmount();
		}
		
		return result.getHomeGoalAmount();
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
