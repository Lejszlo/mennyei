package com.sp.organizer.query.viewer.match.resource;

import org.springframework.hateoas.ResourceSupport;


import lombok.Data;
import lombok.EqualsAndHashCode;
import sp.match.api.value.MatchResult;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper=false)
public class MatchResource extends ResourceSupport {

	private MatchResult matchResult;
	
	private boolean atHome;
	
	private int homeGoalAmount;

	private int awayGoalAmount;
	
	private LocalDateTime matchDate;
	
	private String opponentClubName;
	
	private String opponentClubId;
	
	private String competitionName;
	
}
