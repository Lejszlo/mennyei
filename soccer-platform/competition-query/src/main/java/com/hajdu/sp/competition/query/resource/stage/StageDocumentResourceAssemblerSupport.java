package com.hajdu.sp.competition.query.resource.stage;

import com.hajdu.sp.competition.lib.resource.StageDocumentResource;
import com.hajdu.sp.competition.query.controller.StageDocumentQueryController;
import com.hajdu.sp.competition.query.entity.StageDocument;
import com.hajdu.sp.competition.query.resource.turn.TurnDocumentResourceAssemblerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

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

        stageDocumentResource.add(linkTo(ControllerLinkBuilder.methodOn(StageDocumentQueryController.class).getTable(
                stageDocument.getId().getCompetitionId().getValue(),
                stageDocument.getId().getSeasonId().getSeasonUuid().toString(),
                stageDocument.getId().getStageUuid().toString())).withRel("table"));

        return stageDocumentResource;
    }
}
