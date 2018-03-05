package com.sp.match.api.resource;

import com.sp.match.api.value.MatchResult;
import org.springframework.hateoas.ResourceSupport;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class MatchDocumentResource extends ResourceSupport {

	private int homeGoalAmount;

	private int awayGoalAmount;
	
	private String matchDate;
	
	private String homeClubName;
	
	private String awayClubName;
}
