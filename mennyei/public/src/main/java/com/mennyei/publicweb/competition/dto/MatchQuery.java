package com.mennyei.publicweb.competition.dto;

import org.springframework.data.mongodb.core.mapping.DBRef;

import com.mennyei.core.competition.domain.match.domain.MatchResultType;
import com.mennyei.core.competition.domain.match.domain.Result;
import com.mennyei.publicweb.club.dto.ClubQuery;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MatchQuery {

	private int index;
	
	private String matchDate;
	
	private boolean played;
	
	private boolean isAtHome;
	
	private Result result;
	
	@DBRef
	private ClubQuery opponentClubId;
	
	private MatchResultType resultType;
}
