package com.sp.organizer.query.viewer.competition.controller;

import com.sp.organizer.query.viewer.competition.resource.table.TableResource;
import com.sp.organizer.query.viewer.competition.resource.turn.TurnDocumentResource;
import com.sp.organizer.query.viewer.competition.service.StageDocumentService;
import com.sp.organizer.query.viewer.competition.service.TableDocumentService;
import com.sp.organizer.query.viewer.competition.service.TurnDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sp.organizer.api.resource.ClubDocumentResource;

import static org.springframework.http.ResponseEntity.*;

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
	public Resources<ClubDocumentResource> getClubs(@PathVariable("competitionId") String competitionId, @PathVariable("stageId") String stageId) {
    	return stageDocumentService.getClubs(competitionId, stageId);
	}

	@GetMapping(value = "/tables")
    public TableResource getTable(@PathVariable("competitionId") String competitionId, @PathVariable("stageId") String stageId) {
        return tableDocumentService.getTable(competitionId, stageId);
    }

    @GetMapping(value = "/turns")
    public Resources<TurnDocumentResource> getTurns(@PathVariable("competitionId") String competitionId, @PathVariable("stageId") String stageId) {
        return turnDocumentService.getTurns(competitionId, stageId);
    }
}
