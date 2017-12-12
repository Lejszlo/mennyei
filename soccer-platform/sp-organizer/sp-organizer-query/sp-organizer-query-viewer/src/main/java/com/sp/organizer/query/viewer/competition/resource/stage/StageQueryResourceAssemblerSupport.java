package com.sp.organizer.query.viewer.competition.resource.stage;

import com.sp.organizer.query.viewer.competition.controller.CompetitionQueryController;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;
import com.sp.organizer.query.updater.competition.entity.StageQuery;
import com.sp.organizer.query.viewer.competition.controller.StageQueryController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

@Component
public class StageQueryResourceAssemblerSupport extends ResourceAssemblerSupport<StageQuery, StageQueryResource> {

    public StageQueryResourceAssemblerSupport() {
        super(CompetitionQueryController.class, StageQueryResource.class);
    }

    @Override
    public StageQueryResource toResource(StageQuery stageQuery) {
        StageQueryResource stageqQueryResource = createResourceWithId(stageQuery.getIndex(), stageQuery);
        stageqQueryResource.add(linkTo(methodOn(StageQueryController.class).getClubs(stageQuery.getCompetitionQuery(), stageQuery.getIndex())).withRel("clubs"));
        stageqQueryResource.add(linkTo(methodOn(StageQueryController.class).getTable(stageQuery.getCompetitionQuery(), stageQuery.getIndex())).withRel("table"));
        stageqQueryResource.setInterval(stageQuery.getInterval());
        return stageqQueryResource;
    }
}
