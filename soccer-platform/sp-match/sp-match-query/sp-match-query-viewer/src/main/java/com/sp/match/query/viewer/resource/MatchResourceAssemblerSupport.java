package com.sp.match.query.viewer.resource;

import com.sp.match.api.value.MatchResult;
import com.sp.match.api.value.MatchResultDetails;
import org.springframework.stereotype.Component;
import com.sp.match.api.resource.MatchDocumentResource;
import com.sp.match.query.viewer.controller.MatchDocumentController;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import com.sp.match.query.updater.match.entity.MatchDocument;

@Component
public class MatchResourceAssemblerSupport extends ResourceAssemblerSupport<MatchDocument, MatchDocumentResource> {

	public MatchResourceAssemblerSupport() {
		super(MatchDocumentController.class, MatchDocumentResource.class);
	}

	@Override
	public MatchDocumentResource toResource(MatchDocument matchDocument) {
		MatchDocumentResource resource = createResourceWithId(matchDocument.getId(), matchDocument);
		resource.setMatchDate(matchDocument.getMatchDate());
        resource.setHomeClubName(matchDocument.getHomeClubId().getValue());
        resource.setAwayClubName(matchDocument.getAwayClubId().getValue());
        MatchResultDetails matchResultDetails = matchDocument.getMatchResultDetails();
        if(matchResultDetails != null) {
            resource.setHomeGoalAmount(matchResultDetails.getHomeGoalAmount());
            resource.setAwayGoalAmount(matchResultDetails.getAwayGoalAmount());
        }
        return resource;
	}


}
