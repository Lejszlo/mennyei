package com.sp.organizer.query.viewer.competition.resource.table;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;
import com.sp.organizer.query.updater.competition.entity.TableQuery;
import com.sp.organizer.query.viewer.competition.controller.CompetitionQueryController;

@Component
public class TableQueryResourceAssemblerSupport extends ResourceAssemblerSupport<TableQuery, TableQueryResource> {

    public TableQueryResourceAssemblerSupport() {
        super(CompetitionQueryController.class, TableQueryResource.class);
    }

    @Override
    public TableQueryResource toResource(TableQuery tableQuery) {
        TableQueryResource tableResource = createResourceWithId(tableQuery.getId(), tableQuery);
        tableResource.setRows(tableQuery.getRows());
        return tableResource;
    }
}
