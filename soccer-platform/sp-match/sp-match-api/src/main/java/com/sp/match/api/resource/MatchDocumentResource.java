package com.sp.match.api.resource;

import com.sp.match.api.value.MatchResult;
import com.sp.organizer.api.resource.ClubDocumentResource;
import org.springframework.hateoas.ResourceSupport;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class MatchDocumentResource extends ResourceSupport {

	private int homeGoalAmount;

	private int awayGoalAmount;
	
	private String matchDate;
	
	private ClubDocumentResource homeClubDocumentResource;
	
	private ClubDocumentResource awayClubDocumentResource;
}
