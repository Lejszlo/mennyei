package com.sp.organizer.backend.match.resource;

import com.sp.organizer.backend.match.dto.match.MatchQuery;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.sp.organizer.backend.club.domain.ClubQuery;
import com.sp.organizer.backend.match.controller.MatchController;

public class MatchResourceAssemblerSupport extends ResourceAssemblerSupport<MatchQuery, MatchResource> {

	private String clubId;

	public MatchResourceAssemblerSupport(String clubId) {
		super(MatchController.class, MatchResource.class);
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
		resource.setCompetitionName(matchQuery.getCompetition().getCompetitionInfo().getName());
		if(matchQuery.isPlayed()) {
			resource.setMatchResult(matchQuery.getResultFor(clubId));
			resource.setAwayGoalAmount(matchQuery.getMatchResultDetailes().getAwayGoalAmount());
			resource.setHomeGoalAmount(matchQuery.getMatchResultDetailes().getHomeGoalAmount());
		}
		resource.add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(MatchController.class).byClubWithDetailes(matchQuery.getId())).withRel("detailes"));
		return resource;
	}


}
