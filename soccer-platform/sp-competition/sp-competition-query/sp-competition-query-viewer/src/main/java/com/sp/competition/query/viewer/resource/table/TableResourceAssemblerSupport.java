package com.sp.competition.query.viewer.resource.table;

import com.sp.competition.query.viewer.controller.StageDocumentQueryController;
import com.sp.match.api.resource.MatchDocumentResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.List;

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
