package com.mennyei.publicweb.match.dto;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

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
		resource.setMatchResult(matchQuery.getResultFor(clubId));
		resource.setAtHome(matchQuery.isAtHome(clubId));
		return resource;
	}


}
