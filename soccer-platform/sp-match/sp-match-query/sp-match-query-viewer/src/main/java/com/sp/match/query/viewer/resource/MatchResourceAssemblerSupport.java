package com.sp.match.query.viewer.resource;

import com.sp.match.api.value.MatchResultDetails;
import com.sp.match.query.viewer.controller.ClubDocumentQueryFeignController;
import com.sp.organizer.api.resource.ClubDocumentResource;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Component;
import com.sp.match.api.resource.MatchDocumentResource;
import com.sp.match.query.viewer.controller.MatchDocumentController;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import com.sp.match.query.updater.match.entity.MatchDocument;

import java.util.ArrayList;
import java.util.List;

@Component
public class MatchResourceAssemblerSupport extends ResourceAssemblerSupport<MatchDocument, MatchDocumentResource> {

    private final ClubDocumentQueryFeignController clubDocumentQueryFeignController;

	@Autowired
    public MatchResourceAssemblerSupport(ClubDocumentQueryFeignController clubDocumentQueryFeignController) {
		super(MatchDocumentController.class, MatchDocumentResource.class);
        this.clubDocumentQueryFeignController = clubDocumentQueryFeignController;
    }

    public Resources<MatchDocumentResource> toResourcesWithClubs(List<MatchDocument> matchDocuments)  {

        List<String> clubIds = new ArrayList<>();
        matchDocuments.forEach(matchDocument -> {
            clubIds.add(matchDocument.getHomeClubId().getValue());
            clubIds.add(matchDocument.getAwayClubId().getValue());
        });

        List<ClubDocumentResource> clubDocumentResources = clubDocumentQueryFeignController.getClubs(clubIds);

        List<MatchDocumentResource> matchDocumentResources = Lists.newArrayList();
        matchDocuments
                .forEach(matchDocument -> {
                    MatchDocumentResource matchDocumentResource = toResource(matchDocument);
                    matchDocumentResource.setHomeClubDocumentResource(getClubDocumentResource(clubDocumentResources, matchDocument.getHomeClubId().getValue()));
                    matchDocumentResource.setAwayClubDocumentResource(getClubDocumentResource(clubDocumentResources, matchDocument.getAwayClubId().getValue()));
                    matchDocumentResources.add(matchDocumentResource);
                });
        return new Resources<>(matchDocumentResources);
    }

    private ClubDocumentResource getClubDocumentResource(List<ClubDocumentResource> clubDocumentResources, String clubId) {
        return clubDocumentResources
                                .stream()
                                .filter(clubDocumentResource -> clubDocumentResource.getClubId().equals(clubId))
                                .findFirst()
                                .orElse(new ClubDocumentResource());
    }

    @Override
	public MatchDocumentResource toResource(MatchDocument matchDocument) {
		MatchDocumentResource resource = createResourceWithId(matchDocument.getId(), matchDocument);
		resource.setMatchDate(matchDocument.getMatchDate());
        MatchResultDetails matchResultDetails = matchDocument.getMatchResultDetails();
        if(matchResultDetails != null) {
            resource.setHomeGoalAmount(matchResultDetails.getHomeGoalAmount());
            resource.setAwayGoalAmount(matchResultDetails.getAwayGoalAmount());
        }
        return resource;
	}


}
