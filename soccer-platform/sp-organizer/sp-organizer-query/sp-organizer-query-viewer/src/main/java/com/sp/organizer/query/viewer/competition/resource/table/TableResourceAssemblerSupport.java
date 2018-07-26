package com.sp.organizer.query.viewer.competition.resource.table;

import com.sp.match.api.resource.MatchDocumentResource;
import com.sp.organizer.query.viewer.competition.controller.StageDocumentQueryController;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@Component
public class TableResourceAssemblerSupport extends ResourceAssemblerSupport<List<MatchDocumentResource>, TableResource> {

    public TableResourceAssemblerSupport() {
        super(StageDocumentQueryController.class, TableResource.class);
    }

    @Override
    public TableResource toResource(List<MatchDocumentResource> matchDocuments) {
        TableResource tableResource = new TableResource();
        tableResource.setMatches(matchDocuments);
        return tableResource;
    }
}
