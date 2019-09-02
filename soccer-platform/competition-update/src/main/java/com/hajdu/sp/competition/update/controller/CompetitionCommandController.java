package com.hajdu.sp.competition.update.controller;

import com.hajdu.sp.competition.update.command.competition.*;
import com.hajdu.sp.competition.update.service.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RequestMapping("/competitions")
@RestController
public class CompetitionCommandController {

    private CompetitionService competitionService;

    @Autowired
    public CompetitionCommandController(CompetitionService competitionService) {
        this.competitionService = competitionService;
    }

    @PostMapping
    public String createCompetition(@RequestBody @Validated CreateCompetition competitionCommand) throws ExecutionException, InterruptedException {
        return competitionService.save(competitionCommand);
    }

    @PatchMapping("/{id}/seasons")
    public String addSeason(@PathVariable("id") String id, @RequestBody @Validated AddSeason addSeason) throws ExecutionException, InterruptedException {
        return competitionService.addSeason(id, addSeason);
    }

    @PatchMapping("/{id}/stages")
    public String addStage(@PathVariable("id") String id, @RequestBody @Validated List<AddStage> addStages) throws ExecutionException, InterruptedException {
        return competitionService.addStages(id, addStages);
    }

    @PatchMapping("/{id}/clubs")
    public String addClubs(@PathVariable("id") String id, @RequestBody @Validated List<AddClub> addClubs) throws ExecutionException, InterruptedException {
        return competitionService.addClubs(id, addClubs);
    }

    @PatchMapping("/{id}/turns")
    public String addTurns(@PathVariable("id") String id, @RequestBody @Validated List<AddTurn> addTurns) throws ExecutionException, InterruptedException {
        return competitionService.addTurns(id, addTurns);
    }

    @PatchMapping("/{id}/matches")
    public String addMatches(@PathVariable("id") String id, @RequestBody @Validated List<AddMatch> addMatches) throws ExecutionException, InterruptedException {
        return competitionService.addMatches(id, addMatches);
    }

}
