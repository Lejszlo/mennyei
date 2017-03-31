package com.mennyei.publicweb.match.dto.match;

import org.springframework.hateoas.ResourceSupport;

import com.mennyei.core.match.domain.MatchResult;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class MatchResource extends ResourceSupport {

	private MatchResult matchResult;
	
	private boolean atHome;
	
	private int homeGoalAmount;

	private int awayGoalAmount;
	
	private String matchDate;
	
	private String opponentClubName;
	
	private String opponentClubId;
	
	private String competitionName;
	
}
