package com.sp.organizer.query.viewer.competition.controller;

import com.sp.organizer.query.viewer.competition.resource.turn.TurnDocumentResource;
import com.sp.organizer.query.viewer.competition.service.TurnDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/competition/{competitionId}/{stageIndex}/turns")
public class TurnDocumentQueryController {

    private TurnDocumentService turnDocumentService;

    @Autowired
    public TurnDocumentQueryController(TurnDocumentService turnDocumentService) {
        this.turnDocumentService = turnDocumentService;
    }

    @GetMapping(value = "/{turnIndex}")
    public TurnDocumentResource getTurn(@PathVariable("competitionId") String competitionId,
                                                                             @PathVariable("stageId") String stageId,
                                                                             @PathVariable("turnIndex") int turnIndex) {
        return turnDocumentService.getTurn(competitionId, stageId, turnIndex);
    }

}
