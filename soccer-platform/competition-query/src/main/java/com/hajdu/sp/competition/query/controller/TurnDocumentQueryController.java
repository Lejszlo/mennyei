package com.hajdu.sp.competition.query.controller;

import com.hajdu.sp.competition.lib.resource.TurnDocumentResource;
import com.hajdu.sp.competition.query.service.TurnDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.hajdu.sp.competition.lib.value.StageId.stageId;

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
                                        @PathVariable("seasonId") String seasonId,
                                        @PathVariable("stageId") String stageId,
                                        @PathVariable("turnIndex") int turnIndex) {
        return turnDocumentService.getTurn(stageId(competitionId, seasonId, stageId), turnIndex);
    }

}
