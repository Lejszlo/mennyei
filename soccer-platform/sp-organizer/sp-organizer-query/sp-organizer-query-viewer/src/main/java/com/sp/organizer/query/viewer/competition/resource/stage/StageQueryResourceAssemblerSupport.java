package com.sp.organizer.query.viewer.competition.resource.stage;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;
import com.sp.organizer.query.updater.competition.entity.StageDocument;
import com.sp.organizer.query.viewer.competition.controller.StageDocumentController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

@Component
public class StageQueryResourceAssemblerSupport extends ResourceAssemblerSupport<StageDocument, StageDocumentResource> {

    public StageQueryResourceAssemblerSupport() {
        super(StageDocumentController.class, StageDocumentResource.class);
    }

    @Override
    public StageDocumentResource toResource(StageDocument stageDocument) {
        StageDocumentResource stageDocumentResource = new StageDocumentResource();

        stageDocumentResource.setInterval(stageDocument.getInterval());
        stageDocumentResource.setStageRuleSet(stageDocument.getStageRuleSet());
        stageDocumentResource.setIndex(stageDocument.getIndex());
        stageDocumentResource.setName(stageDocument.getName());

        stageDocumentResource.add(linkTo(methodOn(StageDocumentController.class).getClubs(stageDocument.getCompetitionDocumentId(), stageDocument.getIndex())).withRel("clubs"));
        stageDocumentResource.add(linkTo(methodOn(StageDocumentController.class).getTable(stageDocument.getCompetitionDocumentId(), stageDocument.getIndex())).withRel("table"));
        stageDocumentResource.add(linkTo(methodOn(StageDocumentController.class).getTurns(stageDocument.getCompetitionDocumentId(), stageDocument.getIndex())).withRel("turns"));

        return stageDocumentResource;
    }
}
