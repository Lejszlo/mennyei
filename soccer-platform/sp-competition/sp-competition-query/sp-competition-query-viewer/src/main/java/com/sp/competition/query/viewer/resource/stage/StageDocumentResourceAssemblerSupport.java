package com.sp.competition.query.viewer.resource.stage;

import com.sp.competition.query.updater.entity.StageDocument;
import com.sp.competition.query.viewer.controller.StageDocumentQueryController;
import com.sp.competition.query.viewer.resource.turn.TurnDocumentResourceAssemblerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;
import sp.competition.api.resource.StageDocumentResource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

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

//        stageDocumentResource.add(linkTo(methodOn(StageDocumentQueryController.class).getClubs(
//                stageDocument.getCompetitionDocumentId(),
//                stageDocument.getCompetitionDocumentId(),
//                stageDocument.getId())).withRel("clubs"));

        stageDocumentResource.add(linkTo(methodOn(StageDocumentQueryController.class).getTable(
                stageDocument.getId().getCompetitionId().getValue(),
                stageDocument.getId().getSeasonId().getSeasonUuid().toString(),
                stageDocument.getId().getStageUuid().toString())).withRel("table"));

        return stageDocumentResource;
    }
}
