package com.sp.organizer.query.viewer.match.resource;

import com.sp.organizer.query.viewer.match.controller.MatchQueryController;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import com.sp.organizer.query.updater.club.entity.ClubQuery;
import com.sp.organizer.query.updater.match.entity.MatchQuery;

public class MatchResourceAssemblerSupport extends ResourceAssemblerSupport<MatchQuery, MatchResource> {

	private String clubId;

	public MatchResourceAssemblerSupport(String clubId) {
		super(MatchQueryController.class, MatchResource.class);
		this.clubId = clubId;
	}

	@Override
	public MatchResource toResource(MatchQuery matchQuery) {
		MatchResource resource = createResourceWithId(matchQuery.getId(), matchQuery);
		resource.setAtHome(matchQuery.isAtHome(clubId));
		resource.setMatchDate(matchQuery.getMatchDate());
		ClubQuery opponent = matchQuery.whoIsTheOpponentOf(clubId);
		resource.setOpponentClubName(opponent.getName());
		resource.setOpponentClubId(opponent.getId());
		if(matchQuery.isPlayed()) {
			resource.setMatchResult(matchQuery.getResultFor(clubId));
			resource.setAwayGoalAmount(matchQuery.getMatchResultDetailes().getAwayGoalAmount());
			resource.setHomeGoalAmount(matchQuery.getMatchResultDetailes().getHomeGoalAmount());
		}
		resource.add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(MatchQueryController.class).byClubWithDetailes(matchQuery.getId())).withRel("detailes"));
		return resource;
	}


}
