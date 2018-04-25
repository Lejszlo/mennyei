package com.sp.organizer.query.viewer.competition.controller;

import com.sp.organizer.query.viewer.competition.resource.table.TableDocumentResource;
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
public class StageDocumentController {
	private final StageDocumentService stageDocumentService;
	private final TableDocumentService tableQueryService;
	private final TurnDocumentService turnDocumentService;

    @Autowired
    public StageDocumentController(StageDocumentService stageDocumentService, TableDocumentService tableQueryService, TurnDocumentService turnDocumentService) {
        this.stageDocumentService = stageDocumentService;
        this.tableQueryService = tableQueryService;
        this.turnDocumentService = turnDocumentService;
    }

    @GetMapping(value = "/clubs")
	public ResponseEntity<Resources<ClubDocumentResource>> getClubs(@PathVariable("competitionId") String competitionId, @PathVariable("stageId") String stageId) {
    	return ok(stageDocumentService.getClubs(competitionId, stageId));
	}

	@GetMapping(value = "/tables")
    public TableDocumentResource getTable(@PathVariable("competitionId") String competitionId, @PathVariable("stageId") String stageId) {
        return tableQueryService.getTable(competitionId, stageId);
    }

    @GetMapping(value = "/turns")
    public Resources<TurnDocumentResource> getTurns(@PathVariable("competitionId") String competitionId, @PathVariable("stageId") String stageId) {
        return turnDocumentService.getTurns(competitionId, stageId);
    }
}
