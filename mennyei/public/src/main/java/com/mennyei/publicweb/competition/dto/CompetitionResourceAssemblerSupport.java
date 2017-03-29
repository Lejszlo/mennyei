package com.mennyei.publicweb.competition.dto;

import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.mennyei.publicweb.competition.controller.CompetitionController;

public class CompetitionResourceAssemblerSupport
		extends ResourceAssemblerSupport<CompetitionQuery, CompetitionResource> {

	public CompetitionResourceAssemblerSupport() {
		super(CompetitionController.class, CompetitionResource.class);
	}

	@Override
	public CompetitionResource toResource(CompetitionQuery competitionQuery) {
		CompetitionResource resource = createResourceWithId(competitionQuery.getId(), competitionQuery);
		resource.add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(CompetitionController.class)
				.getCompetitionTables(competitionQuery.getId())).withRel("tables"));
		return resource;
	}

}
