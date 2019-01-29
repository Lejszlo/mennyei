package com.hajdu.sp.competition.query.resource.table;

import com.hajdu.sp.competition.query.controller.StageDocumentQueryController;
import com.hajdu.sp.match.lib.resource.MatchDocumentResource;
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
