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

	public boolean isAtHome(String clubId) {
		return clubId.equals(homeClubId);
	}

	public void calculateResult() {
		result = Result.builder(getHomeGoalAmount(), getAwayGoalAmount()).build();
	}

	private int getHomeGoalAmount() {
		return filterEvents(homeClubevents, new HashSet<>(Arrays.asList(MatchEventType.GOAL))).size();
	}

	private int getAwayGoalAmount() {
		return filterEvents(awayClubevents, new HashSet<>(Arrays.asList(MatchEventType.GOAL))).size();
	}

	private List<MatchEvent> filterEvents(List<MatchEvent> events, Set<MatchEventType> eventTypes) {
		return events.stream().filter(e -> eventTypes.contains(e.getMatchEventType())).collect(Collectors.toList());
	}
}
