package com.sp.match.query.viewer.resource;

import com.google.common.collect.Lists;
import com.sp.club.api.controller.ClubDocumentQueryClient;
import com.sp.club.api.resource.ClubDocumentResource;
import com.sp.match.api.resource.MatchDocumentResource;
import com.sp.match.api.value.MatchResultDetails;
import com.sp.match.query.updater.match.entity.MatchDocument;
import com.sp.match.query.viewer.controller.MatchDocumentQueryClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class MatchResourceAssemblerSupport extends ResourceAssemblerSupport<MatchDocument, MatchDocumentResource> {

    private final ClubDocumentQueryClient clubDocumentQueryFeignController;

	@Autowired
    public MatchResourceAssemblerSupport(ClubDocumentQueryClient clubDocumentQueryFeignController) {
		super(MatchDocumentQueryClient.class, MatchDocumentResource.class);
        this.clubDocumentQueryFeignController = clubDocumentQueryFeignController;
    }

    public List<MatchDocumentResource> toResources(List<MatchDocument> matchDocuments)  {
        return getMatchDocumentResources(matchDocuments);
    }

    public Resources<MatchDocumentResource> toResourcesWithClubs(List<MatchDocument> matchDocuments)  {
        return new Resources<>(getMatchDocumentResources(matchDocuments));
    }

    private List<MatchDocumentResource> getMatchDocumentResources(List<MatchDocument> matchDocuments) {
        Set<String> clubIds = new HashSet<>();
        matchDocuments.forEach(matchDocument -> {
            clubIds.add(matchDocument.getHomeClubId().getId());
            clubIds.add(matchDocument.getAwayClubId().getId());
        });

        List<ClubDocumentResource> clubDocumentResources = clubDocumentQueryFeignController.getClubs(Lists.newArrayList(clubIds));

        List<MatchDocumentResource> matchDocumentResources = Lists.newArrayList();
        matchDocuments
                .forEach(matchDocument -> {
                    MatchDocumentResource matchDocumentResource = toResource(matchDocument);
                    matchDocumentResource.setHomeClubDocumentResource(getClubDocumentResource(clubDocumentResources, matchDocument.getHomeClubId().getId()));
                    matchDocumentResource.setAwayClubDocumentResource(getClubDocumentResource(clubDocumentResources, matchDocument.getAwayClubId().getId()));
                    matchDocumentResources.add(matchDocumentResource);
                });
        return matchDocumentResources;
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
        resource.setPlayed(matchResultDetails != null);
        if(matchResultDetails != null) {
            resource.setHomeGoalAmount(matchResultDetails.getHomeGoalAmount());
            resource.setAwayGoalAmount(matchResultDetails.getAwayGoalAmount());
            resource.setHomeYellowCardAmount(matchResultDetails.getHomeYellowCardAmount());
            resource.setAwayYellowCardAmount(matchResultDetails.getAwayYellowCardAmount());
            resource.setHomeRedCardAmount(matchResultDetails.getHomeRedCardAmount());
            resource.setHomeRedCardAmount(matchResultDetails.getHomeRedCardAmount());
            resource.setWinnerType(matchResultDetails.getWinner());
            resource.setHomeClubId(matchDocument.getHomeClubId().getId());
            resource.setAwayClubId(matchDocument.getAwayClubId().getId());
        }
        return resource;
	}

}
