package com.sp.organizer.query.viewer.competition.resource;

import com.sp.organizer.api.resource.SeasonDocumentResource;
import com.sp.organizer.query.updater.competition.entity.SeasonDocument;
import com.sp.organizer.query.viewer.competition.controller.CompetitionDocumentQueryController;
import com.sp.organizer.query.viewer.competition.controller.StageDocumentQueryController;
import com.sp.organizer.query.viewer.competition.resource.stage.StageDocumentResourceAssemblerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

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
