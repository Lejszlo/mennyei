package com.sp.organizer.query.viewer.competition.service;

import com.sp.organizer.query.updater.competition.entity.TurnDocument;
import com.sp.organizer.query.viewer.competition.resource.table.TableDocumentResource;
import com.sp.organizer.query.viewer.competition.resource.table.TableDocumentResourceAssemblerSupport;
import com.sp.organizer.query.viewer.competition.resource.turn.TurnDocumentResource;
import com.sp.organizer.query.viewer.competition.resource.turn.TurnDocumentResourceAssemblerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Service;
import com.sp.organizer.query.updater.competition.entity.StageDocument;
import com.sp.organizer.query.updater.competition.entity.TableQuery;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class TableDocumentService {

    private final StageDocumentService stageDocumentService;

    private final TableDocumentResourceAssemblerSupport tableDocumentResourceAssemblerSupport;

    private TurnDocumentResourceAssemblerSupport turnDocumentResourceAssemblerSupport;

    @Autowired
    public TableDocumentService(StageDocumentService stageDocumentService,
                                TableDocumentResourceAssemblerSupport tableDocumentResourceAssemblerSupport,
                                TurnDocumentResourceAssemblerSupport turnDocumentResourceAssemblerSupport) {
        this.stageDocumentService = stageDocumentService;
        this.tableDocumentResourceAssemblerSupport = tableDocumentResourceAssemblerSupport;
        this.turnDocumentResourceAssemblerSupport = turnDocumentResourceAssemblerSupport;
    }

    public TableDocumentResource getTable(String competitionId, int stageIndex) {
        Optional<StageDocument> stageDocumentOptional = stageDocumentService.getStageQuery(competitionId, stageIndex);
        TableQuery tableQuery = stageDocumentOptional.map(StageDocument::getTableQuery).orElse(null);
        return tableDocumentResourceAssemblerSupport.toResource(tableQuery);
    }

}
