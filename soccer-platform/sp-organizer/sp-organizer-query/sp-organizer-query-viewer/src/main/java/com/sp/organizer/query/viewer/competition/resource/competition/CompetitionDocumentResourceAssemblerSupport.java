package com.sp.organizer.query.viewer.competition.resource.competition;

import com.sp.organizer.api.resource.CompetitionDocumentResource;
import com.sp.organizer.query.updater.competition.entity.CompetitionDocument;
import com.sp.organizer.query.viewer.competition.controller.CompetitionDocumentQueryController;
import com.sp.organizer.query.viewer.competition.resource.SeasonDocumentResourceAssemblerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class CompetitionDocumentResourceAssemblerSupport
		extends ResourceAssemblerSupport<CompetitionDocument, CompetitionDocumentResource> {

	@Autowired
	private SeasonDocumentResourceAssemblerSupport seasonDocumentResourceAssemblerSupport;

	public CompetitionDocumentResourceAssemblerSupport() {
		super(CompetitionDocumentQueryController.class, CompetitionDocumentResource.class);
	}

	@Override
	public CompetitionDocumentResource toResource(CompetitionDocument competitionDocument) {
		CompetitionDocumentResource resource = createResourceWithId(competitionDocument.getId(), competitionDocument);

		resource.setCompetitionInfo(competitionDocument.getCompetitionInfo());
		resource.setSeasons(seasonDocumentResourceAssemblerSupport.toResources(competitionDocument.getSeasons()));

		return resource;
	}

}
