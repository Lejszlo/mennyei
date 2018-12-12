package com.sp.competition.query.viewer.resource.competition;

import com.sp.competition.query.updater.entity.CompetitionDocument;
import com.sp.competition.query.viewer.controller.CompetitionDocumentQueryController;
import com.sp.competition.query.viewer.resource.SeasonDocumentResourceAssemblerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;
import sp.competition.api.resource.CompetitionDocumentResource;

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
