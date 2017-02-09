package com.mennyei.publicweb.match.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.rest.core.annotation.RestResource;

import com.mennyei.core.match.domain.MatchHasNotPlayedYetException;
import com.mennyei.core.match.domain.MatchResult;
import com.mennyei.core.match.domain.WinnerType;
import com.mennyei.core.match.domain.event.lineup.LineUp;
import com.mennyei.publicweb.club.dto.ClubQuery;
import com.mennyei.publicweb.competition.dto.CompetitionQuery;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.Singular;

@Builder(builderMethodName="hiddenBuilder")
@Data
@Document
public class MatchQuery {
	
	private String id;

	private int index;
	
	private int fans;
	
	private String matchDate;
	
	private boolean played;
	
	private int homeGoalAmount;

	private int awayGoalAmount;
	
	private String stageName;
	
	private WinnerType winnerType;
	
	@DBRef
	@NonNull
	private ClubQuery homeClub;
	
	@DBRef
	@NonNull
	private ClubQuery awayClub;
	
	@DBRef
	@NonNull
	private CompetitionQuery competition;
	
	@Singular
	@RestResource(exported=false)
	private List<LineUp> homeLineUps = new ArrayList<>();
	
	@Singular
	@RestResource(exported=false)
	private List<LineUp> awayLineUps = new ArrayList<>();
	
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
			return homeGoalAmount;
		}
		return awayGoalAmount;
	}

	public MatchResult getResultFor(String clubId) {
		if(!isPlayed()) {
			throw new MatchHasNotPlayedYetException();
		}
		
		if (WinnerType.DRAW.equals(winnerType)) {
			return MatchResult.DRAW;
		}
		if (WinnerType.HOME.equals(winnerType) && isAtHome(clubId) || WinnerType.AWAY.equals(winnerType) && !isAtHome(clubId)) {
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
			return homeGoalAmount;
		}
		return awayGoalAmount;
	}

	public int getConcernedGoalAmount(String clubId) {
		if(!isPlayed()) {
			throw new MatchHasNotPlayedYetException();
		}
		
		if(awayClub.getId().equals(clubId)) {
			return awayGoalAmount;
		}
		
		return homeGoalAmount;
	}
	
}
