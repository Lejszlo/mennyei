package com.mennyei.publicweb.match.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mennyei.core.match.domain.MatchHasNotPlayedYetException;
import com.mennyei.core.match.domain.MatchResult;
import com.mennyei.core.match.domain.MatchResultType;
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
	
	private MatchResultType matchResultType;
	
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
	private List<LineUp> homeLineUps = new ArrayList<>();
	
	@Singular
	private List<LineUp> awayLineUps = new ArrayList<>();
	
	public static MatchQueryBuilder builder(String matchId, ClubQuery homeClub, ClubQuery awayClub, CompetitionQuery competition) {
		return hiddenBuilder().id(matchId).homeClub(homeClub).awayClub(awayClub).competition(competition);
	}
	
	public ClubQuery whoIsTheOpponentOf(ClubQuery club) {
		if (club.equals(homeClub)) {
			return awayClub;
		}
		return homeClub;
	}

	public ClubQuery whoIsMe(ClubQuery club) {
		if (club.equals(homeClub)) {
			return homeClub;
		}
		return awayClub;
	}

	public int getGoalAmountFor(ClubQuery club) {
		if (homeClub.equals(club)) {
			return homeGoalAmount;
		}
		return awayGoalAmount;
	}

	public MatchResult getResultFor(ClubQuery club) {
		if (MatchResultType.DRAW.equals(matchResultType)) {
			return MatchResult.DRAW;
		}
		if (MatchResultType.HOME.equals(matchResultType) && isAtHome(club) || MatchResultType.AWAY.equals(matchResultType) && !isAtHome(club)) {
			return MatchResult.WIN;
		}
		return MatchResult.LOSE;
	}

	public boolean isAtHome(ClubQuery club) {
		return club.equals(homeClub);
	}

	public int getScoredGoalAmount(ClubQuery club) throws MatchHasNotPlayedYetException {
		if(!isPlayed()) {
			throw new MatchHasNotPlayedYetException();
		}
		
		if(homeClub.equals(club)) {
			return homeGoalAmount;
		}
		return awayGoalAmount;
	}

	public int getConcernedGoalAmount(ClubQuery club) throws MatchHasNotPlayedYetException {
		if(!isPlayed()) {
			throw new MatchHasNotPlayedYetException();
		}
		
		if(awayClub.equals(club)) {
			return awayGoalAmount;
		}
		
		return homeGoalAmount;
	}
	
}
