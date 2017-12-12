package com.sp.organizer.query.viewer.competition.service;

import com.sp.organizer.query.viewer.competition.resource.table.TableQueryResource;
import com.sp.organizer.query.viewer.competition.resource.table.TableQueryResourceAssemblerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sp.organizer.query.updater.competition.entity.StageQuery;
import com.sp.organizer.query.updater.competition.entity.TableQuery;

import java.util.Optional;

@Service
public class TableQueryService {

    private final StageQueryService stageQueryService;

    private final TableQueryResourceAssemblerSupport tableQueryResourceAssemblerSupport;

    @Autowired
    public TableQueryService(StageQueryService stageQueryService, TableQueryResourceAssemblerSupport tableQueryResourceAssemblerSupport) {
        this.stageQueryService = stageQueryService;
        this.tableQueryResourceAssemblerSupport = tableQueryResourceAssemblerSupport;
    }

    public TableQueryResource getTable(String competitionId, int stageIndex) {
        Optional<StageQuery> stageQueryOptional = stageQueryService.getStageQuery(competitionId, stageIndex);
        TableQuery tableQuery = stageQueryOptional.map(StageQuery::getTableQuery).orElse(null);
        return tableQueryResourceAssemblerSupport.toResource(tableQuery);
    }
}
