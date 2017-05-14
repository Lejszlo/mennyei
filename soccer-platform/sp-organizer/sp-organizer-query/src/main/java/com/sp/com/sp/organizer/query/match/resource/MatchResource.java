package com.sp.organizer.backend.match.resource;

import com.sp.core.backend.value.match.MatchResult;
import org.springframework.hateoas.ResourceSupport;


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
