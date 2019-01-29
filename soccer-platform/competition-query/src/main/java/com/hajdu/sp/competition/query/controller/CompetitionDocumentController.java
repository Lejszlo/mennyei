package com.hajdu.sp.competition.query.controller;

import com.hajdu.sp.competition.lib.controller.CompetitionDocumentQueryClient;
import com.hajdu.sp.competition.lib.resource.CompetitionDocumentResource;
import com.hajdu.sp.competition.lib.resource.SeasonDocumentResource;
import com.hajdu.sp.competition.lib.resource.StageDocumentResource;
import com.hajdu.sp.competition.lib.value.CompetitionId;
import com.hajdu.sp.competition.lib.value.SeasonId;
import com.hajdu.sp.competition.query.service.CompetitionDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static com.hajdu.sp.competition.lib.value.CompetitionId.*;
import static com.hajdu.sp.competition.lib.value.SeasonId.*;


@RequestMapping("/api/competition/")
@RestController
public class CompetitionDocumentController implements CompetitionDocumentQueryClient {

    private final CompetitionDocumentService competitionDocumentService;

    @Autowired
    public CompetitionDocumentController(CompetitionDocumentService competitionDocumentService) {
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
    public Resources<StageDocumentResource> getStagesOfCompetition(@PathVariable("competitionId") String competitionId, @PathVariable("seasonId") String seasonId) {
        return competitionDocumentService.getStages(seasonId(competitionId(competitionId), UUID.fromString(seasonId)));
    }
}

