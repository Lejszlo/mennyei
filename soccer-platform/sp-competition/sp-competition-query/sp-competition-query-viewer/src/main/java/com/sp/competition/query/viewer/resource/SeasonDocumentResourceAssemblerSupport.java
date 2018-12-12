package com.sp.competition.query.viewer.resource;

import com.sp.competition.query.updater.entity.SeasonDocument;
import com.sp.competition.query.viewer.controller.CompetitionDocumentQueryController;
import com.sp.competition.query.viewer.controller.StageDocumentQueryController;
import com.sp.competition.query.viewer.resource.stage.StageDocumentResourceAssemblerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;
import sp.competition.api.resource.SeasonDocumentResource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

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

        seasonDocumentResource.add(linkTo(methodOn(CompetitionDocumentQueryController.class).getSeasonsOfCompetition(seasonDocument.getCompetitionDocumentId())).withRel("seasons"));

        return seasonDocumentResource;
    }
}
