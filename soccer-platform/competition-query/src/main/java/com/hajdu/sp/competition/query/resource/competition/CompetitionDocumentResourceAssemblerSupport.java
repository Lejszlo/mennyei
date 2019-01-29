package com.hajdu.sp.competition.query.resource.competition;

import com.hajdu.sp.competition.lib.resource.CompetitionDocumentResource;
import com.hajdu.sp.competition.query.controller.CompetitionDocumentController;
import com.hajdu.sp.competition.query.entity.CompetitionDocument;
import com.hajdu.sp.competition.query.resource.SeasonDocumentResourceAssemblerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.*;
import org.springframework.hateoas.core.LinkBuilderSupport;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class CompetitionDocumentResourceAssemblerSupport
		extends ResourceAssemblerSupport<CompetitionDocument, CompetitionDocumentResource> {

	@Autowired
	private SeasonDocumentResourceAssemblerSupport seasonDocumentResourceAssemblerSupport;

	public CompetitionDocumentResourceAssemblerSupport() {
		super(CompetitionDocumentController.class, CompetitionDocumentResource.class);
	}

	@Override
	public CompetitionDocumentResource toResource(CompetitionDocument competitionDocument) {
		CompetitionDocumentResource resource = createResourceWithId(competitionDocument.getId(), competitionDocument);

		resource.setCompetitionInfo(competitionDocument.getCompetitionInfo());
		resource.setSeasons(seasonDocumentResourceAssemblerSupport.toResources(competitionDocument.getSeasons()));

		resource.add(new Link(new UriTemplate("api/base", new TemplateVariables(new TemplateVariable("projection", TemplateVariable.VariableType.REQUEST_PARAM))), "location"));

		return resource;
	}

}
