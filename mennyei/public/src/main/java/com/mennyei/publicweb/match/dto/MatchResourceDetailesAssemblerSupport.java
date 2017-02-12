package com.mennyei.publicweb.match.dto;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.mennyei.publicweb.match.controller.MatchController;

public class MatchResourceDetailesAssemblerSupport extends ResourceAssemblerSupport<MatchQuery, MatchDetailesResource> {


	public MatchResourceDetailesAssemblerSupport() {
		super(MatchController.class, MatchDetailesResource.class);
	}

	@Override
	public MatchDetailesResource toResource(MatchQuery matchQuery) {
		MatchDetailesResource resource = createResourceWithId(matchQuery.getId(), matchQuery);
		resource.setAwayLineUps(matchQuery.getAwayLineUps());
		resource.setHomeLineUps(matchQuery.getHomeLineUps());
		resource.setTotalRedCardAmount(0);
		resource.setTotalYellowCardAmount(0);
		return resource;
	}


}
