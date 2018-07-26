package com.sp.organizer.query.viewer.competition.resource.stage;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;
import com.sp.organizer.query.updater.competition.entity.StageDocument;
import com.sp.organizer.query.viewer.competition.controller.StageDocumentQueryController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

@Component
public class StageQueryResourceAssemblerSupport extends ResourceAssemblerSupport<StageDocument, StageDocumentResource> {

    public StageQueryResourceAssemblerSupport() {
        super(StageDocumentQueryController.class, StageDocumentResource.class);
    }

    @Override
    public StageDocumentResource toResource(StageDocument stageDocument) {
        StageDocumentResource stageDocumentResource = new StageDocumentResource();

        stageDocumentResource.setInterval(stageDocument.getInterval());
        stageDocumentResource.setStageRuleSet(stageDocument.getStageRuleSet());
        stageDocumentResource.setStageId(stageDocument.getId());
        stageDocumentResource.setName(stageDocument.getName());

        stageDocumentResource.add(linkTo(methodOn(StageDocumentQueryController.class).getClubs(stageDocument.getCompetitionDocumentId(), stageDocument.getId())).withRel("clubs"));
        stageDocumentResource.add(linkTo(methodOn(StageDocumentQueryController.class).getTable(stageDocument.getCompetitionDocumentId(), stageDocument.getId())).withRel("table"));
        stageDocumentResource.add(linkTo(methodOn(StageDocumentQueryController.class).getTurns(stageDocument.getCompetitionDocumentId(), stageDocument.getId())).withRel("turns"));

        return stageDocumentResource;
    }
}
