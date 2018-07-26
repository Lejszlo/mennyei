package com.sp.match.query.viewer.resource;

import com.sp.match.api.value.MatchResultDetails;
import com.sp.match.query.viewer.controller.ClubDocumentQueryFeignController;
import com.sp.organizer.api.resource.ClubDocumentResource;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Component;
import com.sp.match.api.resource.MatchDocumentResource;
import com.sp.match.query.viewer.controller.MatchDocumentQueryClient;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import com.sp.match.query.updater.match.entity.MatchDocument;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class MatchResourceAssemblerSupport extends ResourceAssemblerSupport<MatchDocument, MatchDocumentResource> {

    private final ClubDocumentQueryFeignController clubDocumentQueryFeignController;

	@Autowired
    public MatchResourceAssemblerSupport(ClubDocumentQueryFeignController clubDocumentQueryFeignController) {
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
            clubIds.add(matchDocument.getHomeClubId().getValue());
            clubIds.add(matchDocument.getAwayClubId().getValue());
        });

        List<ClubDocumentResource> clubDocumentResources = clubDocumentQueryFeignController.getClubs(Lists.newArrayList(clubIds));

        List<MatchDocumentResource> matchDocumentResources = Lists.newArrayList();
        matchDocuments
                .forEach(matchDocument -> {
                    MatchDocumentResource matchDocumentResource = toResource(matchDocument);
                    matchDocumentResource.setHomeClubDocumentResource(getClubDocumentResource(clubDocumentResources, matchDocument.getHomeClubId().getValue()));
                    matchDocumentResource.setAwayClubDocumentResource(getClubDocumentResource(clubDocumentResources, matchDocument.getAwayClubId().getValue()));
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
            resource.setHomeClubId(matchDocument.getHomeClubId().getValue());
            resource.setAwayClubId(matchDocument.getAwayClubId().getValue());
        }
        return resource;
	}

}
