package com.sp.organizer.query.viewer.competition.controller;

import com.sp.organizer.api.resource.ClubDocumentResource;
import com.sp.organizer.api.resource.TurnDocumentResource;
import com.sp.organizer.query.viewer.competition.resource.table.TableResource;
import com.sp.organizer.query.viewer.competition.service.StageDocumentService;
import com.sp.organizer.query.viewer.competition.service.TableDocumentService;
import com.sp.organizer.query.viewer.competition.service.TurnDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.sp.organizer.api.value.competition.StageId.stageId;

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

    @GetMapping(value = "/clubs")
    public Resources<ClubDocumentResource> getClubs(@PathVariable("competitionId") String competitionId, @PathVariable("seasonId") String seasonId, @PathVariable("stageId") String stageId) {
        return stageDocumentService.getClubs(stageId(competitionId, seasonId, stageId));
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
