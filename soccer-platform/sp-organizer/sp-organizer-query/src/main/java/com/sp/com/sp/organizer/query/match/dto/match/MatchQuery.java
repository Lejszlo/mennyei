package com.sp.organizer.backend.match.dto.match;

import com.sp.organizer.backend.club.domain.ClubQuery;
import com.sp.organizer.backend.competition.dto.competition.CompetitionQuery;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import com.sp.organizer.backend.match.domain.MatchHasNotPlayedYetException;
import com.sp.core.backend.value.match.MatchResult;
import com.sp.core.backend.value.match.MatchResultDetailes;
import com.sp.core.backend.value.match.WinnerType;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder(builderMethodName="hiddenBuilder")
@Data
@Document
public class MatchQuery {
	
	private String id;

	private int index;
	
	private int fans;
	
	private String matchDate;
	
	private boolean played;
	
	private String stageName;
	
	@DBRef(lazy = true)
	@NonNull
	private ClubQuery homeClub;
	
	@DBRef(lazy = true)
	@NonNull
	private ClubQuery awayClub;
	
	@DBRef(lazy = true)
	@NonNull
	private CompetitionQuery competition;

	private MatchResultDetailes matchResultDetailes;
	
	public static MatchQueryBuilder builder(String matchId, ClubQuery homeClub, ClubQuery awayClub, CompetitionQuery competition) {
		return hiddenBuilder().id(matchId).homeClub(homeClub).awayClub(awayClub).competition(competition);
	}
	
	public ClubQuery whoIsTheOpponentOf(String clubId) {
		if (clubId.equals(homeClub.getId())) {
			return awayClub;
		}
		return homeClub;
	}

	public ClubQuery whoIsMe(String clubId) {
		if (clubId.equals(homeClub.getId())) {
			return homeClub;
		}
		return awayClub;
	}

	public int getGoalAmountFor(String clubId) {
		if (homeClub.getId().equals(clubId)) {
			return matchResultDetailes.getHomeGoalAmount();
		}
		return matchResultDetailes.getAwayGoalAmount();
	}

	public MatchResult getResultFor(String clubId) {
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

	public boolean isAtHome(String clubId) {
		return clubId.equals(homeClub.getId());
	}

	public int getScoredGoalAmount(String clubId) {
		if(!isPlayed()) {
			throw new MatchHasNotPlayedYetException();
		}
		
		if(homeClub.getId().equals(clubId)) {
			return matchResultDetailes.getHomeGoalAmount();
		}
		return matchResultDetailes.getAwayGoalAmount();
	}

	public int getConcernedGoalAmount(String clubId) {
		if(!isPlayed()) {
			throw new MatchHasNotPlayedYetException();
		}
		
		if(awayClub.getId().equals(clubId)) {
			return matchResultDetailes.getAwayGoalAmount();
		}
		
		return matchResultDetailes.getHomeGoalAmount();
	}
	
}
