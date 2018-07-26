package com.sp.match.api.resource;

import com.sp.match.api.value.WinnerType;
import com.sp.organizer.api.resource.ClubDocumentResource;
import org.springframework.hateoas.ResourceSupport;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class  MatchDocumentResource extends ResourceSupport {

    private boolean played;

	private int homeGoalAmount;

	private int awayGoalAmount;

    private int homeYellowCardAmount;

    private int awayYellowCardAmount;

    private int homeRedCardAmount;

    private int awayRedCardAmount;

	private String matchDate;

	private WinnerType winnerType;
	
	private ClubDocumentResource homeClubDocumentResource;
	
	private ClubDocumentResource awayClubDocumentResource;

	private String homeClubId;

	private String awayClubId;
}
