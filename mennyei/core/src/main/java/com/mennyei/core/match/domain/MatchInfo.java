package com.mennyei.core.match.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder(builderMethodName = "hiddenBuilder")
@AllArgsConstructor
public class MatchInfo {
	@NonNull
	private String matchDate;

	private boolean played;
	
	private int fans;

	private ResultGoals resultGoals;

	@NonNull
	private String homeClubId;

	@NonNull
	private String awayClubId;
	
	@NonNull
	private String competitionId;
	
	private String stageName;
	
	private int index;

	public static MatchInfoBuilder builder(String homeClubId, String awayClubId, String matchDate) {
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
			return resultGoals.getHomeGoalAmount();
		}
		return resultGoals.getAwayGoalAmount();
	}

	public MatchResult getResultFor(String clubId) {
		MatchResultType whoIsTheWinner = resultGoals.whoIsTheWinner();
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

	public int getScoredGoalAmount(String clubId) throws MatchHasNotPlayedYetException {
		if(resultGoals == null) {
			throw new MatchHasNotPlayedYetException();
		}
		
		if(homeClubId.equals(clubId)) {
			return resultGoals.getHomeGoalAmount();
		}
		return resultGoals.getAwayGoalAmount();
	}

	public int getConcernedGoalAmount(String clubId) throws MatchHasNotPlayedYetException {
		if(resultGoals == null) {
			throw new MatchHasNotPlayedYetException();
		}
		
		if(awayClubId.equals(clubId)) {
			return resultGoals.getAwayGoalAmount();
		}
		
		return resultGoals.getHomeGoalAmount();
	}
	
}
