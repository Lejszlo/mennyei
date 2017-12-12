package com.sp.organizer.query.viewer.competition.resource.competition;

import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import org.springframework.stereotype.Component;
import com.sp.organizer.query.updater.competition.entity.CompetitionQuery;
import com.sp.organizer.query.viewer.competition.controller.CompetitionQueryController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

@Component
public class CompetitionQueryResourceAssemblerSupport
		extends ResourceAssemblerSupport<CompetitionQuery, CompetitionQueryResource> {

	public CompetitionQueryResourceAssemblerSupport() {
		super(CompetitionQueryController.class, CompetitionQueryResource.class);
	}

	@Override
	public CompetitionQueryResource toResource(CompetitionQuery competitionQuery) {
		CompetitionQueryResource resource = createResourceWithId(competitionQuery.getId(), competitionQuery);
		resource.add(ControllerLinkBuilder.linkTo(methodOn(CompetitionQueryController.class).getStagesOfCompetition(competitionQuery.getId())).withRel("stages"));
		resource.setCompetitionInfo(competitionQuery.getCompetitionInfo());
		resource.setInterval(competitionQuery.getInterval());
		return resource;
	}

}
