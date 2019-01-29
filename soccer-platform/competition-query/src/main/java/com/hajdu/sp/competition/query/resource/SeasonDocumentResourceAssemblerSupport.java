package com.hajdu.sp.competition.query.resource;

import com.hajdu.sp.competition.lib.resource.SeasonDocumentResource;
import com.hajdu.sp.competition.query.controller.CompetitionDocumentController;
import com.hajdu.sp.competition.query.controller.StageDocumentQueryController;
import com.hajdu.sp.competition.query.entity.SeasonDocument;
import com.hajdu.sp.competition.query.resource.stage.StageDocumentResourceAssemblerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@Component
public class SeasonDocumentResourceAssemblerSupport extends ResourceAssemblerSupport<SeasonDocument, SeasonDocumentResource> {

    @Autowired
    private StageDocumentResourceAssemblerSupport stageDocumentResourceAssemblerSupport;

    public SeasonDocumentResourceAssemblerSupport() {
        super(StageDocumentQueryController.class, SeasonDocumentResource.class);
    }

    @Override
    public SeasonDocumentResource toResource(SeasonDocument seasonDocument) {
        SeasonDocumentResource seasonDocumentResource = new SeasonDocumentResource();

        seasonDocumentResource.setInterval(seasonDocument.getInterval());
        seasonDocumentResource.setSeasonId(seasonDocument.getId());
        seasonDocumentResource.setName(seasonDocument.getName());
        seasonDocumentResource.setStages(stageDocumentResourceAssemblerSupport.toResources(seasonDocument.getStages()));

        seasonDocumentResource.add(linkTo(ControllerLinkBuilder.methodOn(CompetitionDocumentController.class).getSeasonsOfCompetition(seasonDocument.getCompetitionDocumentId())).withRel("seasons"));

        return seasonDocumentResource;
    }
}
