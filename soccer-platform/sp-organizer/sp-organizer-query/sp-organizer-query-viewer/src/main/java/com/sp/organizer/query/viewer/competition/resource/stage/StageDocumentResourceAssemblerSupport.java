package com.sp.organizer.query.viewer.competition.resource.stage;

import com.sp.organizer.api.resource.StageDocumentResource;
import com.sp.organizer.query.viewer.competition.resource.turn.TurnDocumentResourceAssemblerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;
import com.sp.organizer.query.updater.competition.entity.StageDocument;
import com.sp.organizer.query.viewer.competition.controller.StageDocumentQueryController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

@Component
public class StageDocumentResourceAssemblerSupport extends ResourceAssemblerSupport<StageDocument, StageDocumentResource> {

    @Autowired
    private TurnDocumentResourceAssemblerSupport turnDocumentResourceAssemblerSupport;

    public StageDocumentResourceAssemblerSupport() {
        super(StageDocumentQueryController.class, StageDocumentResource.class);
    }

    @Override
    public StageDocumentResource toResource(StageDocument stageDocument) {
        StageDocumentResource stageDocumentResource = new StageDocumentResource();

        stageDocumentResource.setInterval(stageDocument.getInterval());
        stageDocumentResource.setStageRuleSet(stageDocument.getStageRuleSet());
        stageDocumentResource.setStageId(stageDocument.getId());
        stageDocumentResource.setName(stageDocument.getName());
        stageDocumentResource.setTurns(turnDocumentResourceAssemblerSupport.toResources(stageDocument.getTurns()));

        stageDocumentResource.add(linkTo(methodOn(StageDocumentQueryController.class).getClubs(
                stageDocument.getCompetitionDocumentId(),
                stageDocument.getCompetitionDocumentId(),
                stageDocument.getId())).withRel("clubs"));

        stageDocumentResource.add(linkTo(methodOn(StageDocumentQueryController.class).getTable(
                stageDocument.getCompetitionDocumentId(),
                stageDocument.getSeasonDocumentId(),
                stageDocument.getId())).withRel("table"));

        return stageDocumentResource;
    }
}
