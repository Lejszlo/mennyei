package com.hajdu.sp.competition.update.resource;

import com.hajdu.sp.competition.update.value.match.WinnerType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.ResourceSupport;

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
