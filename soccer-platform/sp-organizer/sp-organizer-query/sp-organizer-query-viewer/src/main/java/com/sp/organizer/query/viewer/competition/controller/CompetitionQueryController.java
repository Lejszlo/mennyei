package com.sp.organizer.query.viewer.competition.controller;

import com.sp.organizer.query.viewer.competition.resource.stage.StageQueryResource;
import com.sp.organizer.query.viewer.competition.service.CompetitionQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sp.organizer.query.viewer.competition.resource.competition.CompetitionQueryResource;

import java.util.Collection;

import static org.springframework.http.ResponseEntity.ok;

@RequestMapping("/api/competition/")
@RestController
public class CompetitionQueryController {

	private final CompetitionQueryService competitionQueryService;

    @Autowired
    public CompetitionQueryController(CompetitionQueryService competitionQueryService) {
        this.competitionQueryService = competitionQueryService;
    }

	@GetMapping("/{competitionId}")
	public ResponseEntity<CompetitionQueryResource> getCompetition(@PathVariable("competitionId") String competitionId) {
		return ok(competitionQueryService.getCompetition(competitionId));
	}

	@RequestMapping(value = "/{competitionId}/stages", method = RequestMethod.GET)
	public ResponseEntity<Collection<StageQueryResource>> getStagesOfCompetition(@PathVariable("competitionId") String competitionId) {
        return ok(competitionQueryService.getStages(competitionId));
	}
}
