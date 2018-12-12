package com.sp.competition.query.viewer.controller;

import com.sp.competition.query.viewer.resource.table.TableResource;
import com.sp.competition.query.viewer.service.StageDocumentService;
import com.sp.competition.query.viewer.service.TableDocumentService;
import com.sp.competition.query.viewer.service.TurnDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sp.competition.api.resource.TurnDocumentResource;

import static sp.competition.api.value.StageId.stageId;

@RequestMapping("/api/competition/{competitionId}/{stageId}")
@RestController
public class StageDocumentQueryController {
    private final StageDocumentService stageDocumentService;
    private final TurnDocumentService turnDocumentService;
    private final TableDocumentService tableDocumentService;

    @Autowired
    public StageDocumentQueryController(StageDocumentService stageDocumentService,
                                        TurnDocumentService turnDocumentService,
                                        TableDocumentService tableDocumentService) {
        this.stageDocumentService = stageDocumentService;
        this.turnDocumentService = turnDocumentService;
        this.tableDocumentService = tableDocumentService;
    }

    @GetMapping(value = "/tables")
    public TableResource getTable(@PathVariable("competitionId") String competitionId, @PathVariable("seasonId") String seasonId, @PathVariable("stageId") String stageId) {
        return tableDocumentService.getTable(stageId(competitionId, seasonId, stageId));
    }

    @GetMapping(value = "/turns")
    public Resources<TurnDocumentResource> getTurns(@PathVariable("competitionId") String competitionId, @PathVariable("seasonId") String seasonId, @PathVariable("stageId") String stageId) {
        return turnDocumentService.getTurns(stageId(competitionId, seasonId, stageId));
    }
}
