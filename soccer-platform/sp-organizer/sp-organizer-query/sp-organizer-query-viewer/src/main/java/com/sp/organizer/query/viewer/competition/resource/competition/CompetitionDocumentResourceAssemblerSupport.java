package com.sp.organizer.query.viewer.competition.resource.competition;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import org.springframework.stereotype.Component;
import com.sp.organizer.query.updater.competition.entity.CompetitionDocument;
import com.sp.organizer.query.viewer.competition.controller.CompetitionDocumentQueryController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

@Component
public class CompetitionDocumentResourceAssemblerSupport
		extends ResourceAssemblerSupport<CompetitionDocument, CompetitionDocumentResource> {

	public CompetitionDocumentResourceAssemblerSupport() {
		super(CompetitionDocumentQueryController.class, CompetitionDocumentResource.class);
	}

	@Override
	public CompetitionDocumentResource toResource(CompetitionDocument competitionDocument) {
		CompetitionDocumentResource resource = createResourceWithId(competitionDocument.getId(), competitionDocument);

		resource.add(linkTo(methodOn(CompetitionDocumentQueryController.class).getStagesOfCompetition(competitionDocument.getId())).withRel("stages"));

		resource.setCompetitionInfo(competitionDocument.getCompetitionInfo());

		return resource;
	}

}
