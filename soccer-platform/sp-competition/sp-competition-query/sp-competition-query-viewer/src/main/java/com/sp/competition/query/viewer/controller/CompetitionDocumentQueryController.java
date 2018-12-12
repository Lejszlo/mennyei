package com.sp.competition.query.viewer.controller;

import com.sp.competition.query.viewer.service.CompetitionDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sp.competition.api.controller.CompetitionDocumentQueryClient;
import sp.competition.api.resource.CompetitionDocumentResource;
import sp.competition.api.resource.SeasonDocumentResource;
import sp.competition.api.resource.StageDocumentResource;
import sp.competition.api.value.SeasonId;


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

