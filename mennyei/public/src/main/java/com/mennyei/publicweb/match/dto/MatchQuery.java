package com.mennyei.publicweb.match.dto;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mennyei.core.match.domain.MatchResult;
import com.mennyei.core.match.domain.Result;
import com.mennyei.publicweb.club.dto.ClubQuery;
import com.mennyei.publicweb.competition.dto.CompetitionQuery;

import lombok.Builder;
import lombok.Data;

@Builder(builderMethodName="hiddenBuilder")
@Data
@Document
public class MatchQuery {
	
	private String id;

	private int index;
	
	private int fans;
	
	private String matchDate;
	
	private boolean played;
	
	private boolean isAtHome;
	
	private int scoredGoalAmount;

	private int concernGoalAmount;
	
	private Result result;
	
	private MatchResult matchResult;
	
	private String stageName;
	
	@DBRef
	private ClubQuery clubId;
	
	@DBRef
	private ClubQuery opponentClubId;
	
	@DBRef
	private CompetitionQuery competition;
	
	public static MatchQueryBuilder builder(String matchId) {
		return hiddenBuilder().id(matchId);
	}
}
