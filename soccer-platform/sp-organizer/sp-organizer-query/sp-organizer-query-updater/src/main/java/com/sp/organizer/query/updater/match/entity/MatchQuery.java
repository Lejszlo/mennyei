package com.sp.organizer.query.updater.match.entity;

import com.sp.core.backend.exception.MatchHasNotPlayedYetException;
import com.sp.organizer.query.updater.club.entity.ClubQuery;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import sp.match.api.value.MatchResultDetails;
import sp.match.api.value.MatchResult;
import sp.match.api.value.WinnerType;

import java.time.LocalDateTime;
import java.util.Comparator;

@Builder(builderMethodName="hiddenBuilder")
@Data
@Document
public class MatchQuery implements Comparable<MatchQuery> {
	
	private String id;

	private int fans;
	
	private LocalDateTime matchDate;
	
	private boolean played;
	
	@DBRef(lazy = true)
	@NonNull
	private ClubQuery homeClub;
	
	@DBRef(lazy = true)
	@NonNull
	private ClubQuery awayClub;
	
	private MatchResultDetails matchResultDetailes;
	
	public static MatchQueryBuilder builder(String matchId, ClubQuery homeClub, ClubQuery awayClub) {
		return hiddenBuilder().id(matchId).homeClub(homeClub).awayClub(awayClub);
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


	@Override
	public int compareTo(MatchQuery that) {
		return Comparator.comparing(MatchQuery::getMatchDate).compare(this, that);
	}
}
