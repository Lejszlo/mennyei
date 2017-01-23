package com.mennyei.publicweb.match.dto;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mennyei.core.match.domain.ResultGoals;
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
	
	private int homeGoalAmount;

	private int awayGoalAmount;
	
	private ResultGoals resultGoals;
	
	private String stageName;
	
	@DBRef
	private ClubQuery homeClubId;
	
	@DBRef
	private ClubQuery awayClubId;
	
	@DBRef
	private CompetitionQuery competition;
	
	public static MatchQueryBuilder builder(String matchId) {
		return hiddenBuilder().id(matchId);
	}
}
