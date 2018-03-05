package com.sp.organizer.query.viewer.competition.controller;

import com.sp.organizer.query.viewer.competition.resource.table.TableDocumentResource;
import com.sp.organizer.query.viewer.competition.resource.turn.TurnDocumentResource;
import com.sp.organizer.query.viewer.competition.service.StageDocumentService;
import com.sp.organizer.query.viewer.competition.service.TableDocumentService;
import com.sp.organizer.query.viewer.competition.service.TurnDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.sp.organizer.query.viewer.club.resource.ClubDocumentResource;

import static org.springframework.http.ResponseEntity.*;

@RequestMapping("/api/competition/{competitionId}/{stageIndex}")
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
	public ResponseEntity<Resources<ClubDocumentResource>> getClubs(@PathVariable("competitionId") String competitionId, @PathVariable("stageIndex") int stageIndex) {
    	return ok(stageDocumentService.getClubs(competitionId, stageIndex));
	}

	@GetMapping(value = "/tables")
    public TableDocumentResource getTable(@PathVariable("competitionId") String competitionId, @PathVariable("stageIndex") int stageIndex) {
        return tableQueryService.getTable(competitionId, stageIndex);
    }

    @GetMapping(value = "/turns")
    public Resources<TurnDocumentResource> getTurns(@PathVariable("competitionId") String competitionId, @PathVariable("stageIndex") int stageIndex) {
        return turnDocumentService.getTurns(competitionId, stageIndex);
    }
}
