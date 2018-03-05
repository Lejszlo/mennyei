package com.sp.organizer.query.viewer.competition.resource.table;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;
import com.sp.organizer.query.updater.competition.entity.TableQuery;
import com.sp.organizer.query.viewer.competition.controller.CompetitionDocumentController;

@Component
public class TableDocumentResourceAssemblerSupport extends ResourceAssemblerSupport<TableQuery, TableDocumentResource> {

    public TableDocumentResourceAssemblerSupport() {
        super(CompetitionDocumentController.class, TableDocumentResource.class);
    }

    @Override
    public TableDocumentResource toResource(TableQuery tableQuery) {
        TableDocumentResource tableResource = new TableDocumentResource();
        tableResource.setRows(tableQuery.getRows());
        return tableResource;
    }
}
