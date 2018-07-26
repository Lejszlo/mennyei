package com.sp.organizer.query.viewer.competition.controller;

import com.sp.organizer.query.viewer.competition.resource.stage.StageDocumentResource;
import com.sp.organizer.query.viewer.competition.service.CompetitionDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sp.organizer.query.viewer.competition.resource.competition.CompetitionDocumentResource;

import static org.springframework.http.ResponseEntity.ok;

@RequestMapping("/api/competition/")
@RestController
public class CompetitionDocumentQueryController {

    private final CompetitionDocumentService competitionDocumentService;

    @Autowired
    public CompetitionDocumentQueryController(CompetitionDocumentService competitionDocumentService) {
        this.competitionDocumentService = competitionDocumentService;
    }

    @GetMapping
    public Resources<CompetitionDocumentResource> getCompetitions() {
        return competitionDocumentService.getCompetitions();
    }

    @GetMapping("/{competitionId}")
    public CompetitionDocumentResource getCompetition(@PathVariable("competitionId") String competitionId) {
        return competitionDocumentService.getCompetition(competitionId);
    }

    @GetMapping(value = "/{competitionId}/stages")
    public Resources<StageDocumentResource> getStagesOfCompetition(@PathVariable("competitionId") String competitionId) {
        return competitionDocumentService.getStages(competitionId);
    }
}

