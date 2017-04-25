package com.mennyei.publicweb.competition.dto.competition;

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
		resource.setCompetitionInfo(competitionQuery.getCompetitionInfo());
		resource.setCompetitionRuleSet(competitionQuery.getCompetitionRuleSet());
		return resource;
	}

}
