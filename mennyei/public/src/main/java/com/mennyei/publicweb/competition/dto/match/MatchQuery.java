package com.mennyei.publicweb.competition.dto.match;

import org.springframework.data.mongodb.core.mapping.DBRef;

import com.mennyei.core.competition.domain.match.domain.MatchResult;
import com.mennyei.core.competition.domain.match.domain.Result;
import com.mennyei.publicweb.club.dto.ClubQuery;

import lombok.Builder;
import lombok.Data;

@Builder(builderMethodName="hiddenBuilder")
@Data
public class MatchQuery {

	private int index;
	
	private int fans;
	
	private String matchDate;
	
	private boolean played;
	
	private boolean isAtHome;
	
	private int scoredGoalAmount;

	private int concernGoalAmount;
	
	private Result result;
	
	private MatchResult matchResult;
	
	@DBRef
	private ClubQuery clubId;
	
	@DBRef
	private ClubQuery opponentClubId;
	
	private String competitionName;
	
	public static MatchQueryBuilder builder(int index) {
		return hiddenBuilder().index(index);
	}
}
