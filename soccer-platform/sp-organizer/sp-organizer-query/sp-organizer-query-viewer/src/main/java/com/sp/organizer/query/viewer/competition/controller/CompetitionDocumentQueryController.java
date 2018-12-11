package com.sp.organizer.query.viewer.competition.controller;

import com.sp.organizer.api.controller.CompetitionDocumentQueryClient;
import com.sp.organizer.api.resource.CompetitionDocumentResource;
import com.sp.organizer.api.resource.SeasonDocumentResource;
import com.sp.organizer.api.resource.StageDocumentResource;
import com.sp.organizer.api.value.competition.SeasonId;
import com.sp.organizer.query.viewer.competition.service.CompetitionDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/api/competition/")
@RestController
public class CompetitionDocumentQueryController implements CompetitionDocumentQueryClient {

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

    @GetMapping(value = "/{competitionId}/seasons")
    public Resources<SeasonDocumentResource> getSeasonsOfCompetition(@PathVariable("competitionId") String competitionId) {
        return competitionDocumentService.getSeasons(competitionId);
    }

    @GetMapping(value = "/{competitionId}/{seasonId}stages")
    public Resources<StageDocumentResource> getStagesOfCompetition(@PathVariable("seasonId") SeasonId seasonId) {
        return competitionDocumentService.getStages(seasonId);
    }
}

