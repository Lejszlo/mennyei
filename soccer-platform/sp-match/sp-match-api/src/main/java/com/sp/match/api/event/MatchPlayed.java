package com.sp.match.api.event;

import com.sp.match.api.value.MatchResult;
import com.sp.match.api.value.MatchResultDetails;
import com.sp.core.backend.exception.MatchHasNotPlayedYetException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import com.sp.match.api.value.WinnerType;
import com.sp.organizer.api.value.club.AwayClubId;
import com.sp.organizer.api.value.club.ClubId;
import com.sp.organizer.api.value.club.HomeClubId;
import com.sp.organizer.api.value.competition.CompetitionId;
import com.sp.organizer.api.value.competition.StageId;

@Builder(builderMethodName = "hiddenBuilder")
@Value
@AllArgsConstructor
public class MatchPlayed implements MatchEvent {
	private int fans;
	
	private boolean played;

	private String competitionId;

	private MatchResultDetails matchResultDetailes;

	private StageId stageId;

    @NonNull
    private HomeClubId homeClubId;

    @NonNull
    private AwayClubId awayClubId;
	
	public static MatchPlayedBuilder builder(HomeClubId homeClubId, AwayClubId awayClubId, MatchResultDetails matchResultDetailes, CompetitionId competitionId, StageId stageId) {
		return hiddenBuilder()
                .homeClubId(homeClubId)
                .awayClubId(awayClubId)
                .matchResultDetailes(matchResultDetailes)
                .competitionId(competitionId.getValue())
                .stageId(stageId);
	}

	public ClubId whoIsTheOpponentOf(ClubId clubId) {
		if (clubId.equals(homeClubId)) {
			return awayClubId;
		}
		return homeClubId;
	}

	public ClubId whoIsMe(ClubId clubId) {
		if (clubId.equals(homeClubId)) {
			return homeClubId;
		}
		return awayClubId;
	}

	public int getGoalAmountFor(ClubId clubId) {
		if (homeClubId.equals(clubId)) {
			return matchResultDetailes.getHomeGoalAmount();
		}
		return matchResultDetailes.getAwayGoalAmount();
	}

	public MatchResult getResultFor(ClubId clubId) {
		if(!isPlayed()) {
			throw new MatchHasNotPlayedYetException();
		}

		WinnerType whoIsTheWinner = matchResultDetailes.whoIsTheWinner();

		if (WinnerType.DRAW.equals(whoIsTheWinner)) {
			return MatchResult.DRAW;
		}
		if (WinnerType.HOME.equals(whoIsTheWinner) && isAtHome(clubId) || WinnerType.AWAY.equals(whoIsTheWinner) && !isAtHome(clubId)) {
			return MatchResult.WIN;
		}
		return MatchResult.LOSE;
	}

	private boolean isAtHome(ClubId clubId) {
		return clubId.equals(homeClubId);
	}

	public int getScoredGoalAmount(ClubId clubId) {
		if(!isPlayed()) {
			throw new MatchHasNotPlayedYetException();
		}

		if(homeClubId.equals(clubId)) {
			return matchResultDetailes.getHomeGoalAmount();
		}
		return matchResultDetailes.getAwayGoalAmount();
	}

	public int getConcernedGoalAmount(ClubId clubId) {
		if(!isPlayed()) {
			throw new MatchHasNotPlayedYetException();
		}

		if(awayClubId.equals(clubId)) {
			return matchResultDetailes.getAwayGoalAmount();
		}

		return matchResultDetailes.getHomeGoalAmount();
	}


}
