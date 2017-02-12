package com.mennyei.publicweb.match.dto;

import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.mennyei.publicweb.club.dto.ClubQuery;
import com.mennyei.publicweb.match.controller.MatchController;

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
		resource.setAwayGoalAmount(matchQuery.getAwayGoalAmount());
		resource.setHomeGoalAmount(matchQuery.getHomeGoalAmount());
		resource.setMatchDate(matchQuery.getMatchDate());
		ClubQuery opponent = matchQuery.whoIsTheOpponentOf(clubId);
		resource.setOpponentClubName(opponent.getName());
		resource.setOpponentClubId(opponent.getId());
		resource.setCompetitionName(matchQuery.getCompetition().getCompetitionInfo().getName());
		if(matchQuery.isPlayed()) {
			resource.setMatchResult(matchQuery.getResultFor(clubId));
		}
		resource.add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(MatchController.class).byClubWithDetailes(matchQuery.getId())).withRel("detailes"));
		return resource;
	}


}
