package com.hajdu.sp.competition.update.controller;

import com.hajdu.sp.competition.update.command.competition.*;
import com.hajdu.sp.competition.update.service.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RequestMapping("/competition")
@RestController
public class CompetitionCommandController {

    private CompetitionService competitionService;

    @Autowired
    public CompetitionCommandController(CompetitionService competitionService) {
        this.competitionService = competitionService;
    }

    @PostMapping
    public String createCompetition(@RequestBody CreateCompetition competitionCommand) throws ExecutionException, InterruptedException {
        return competitionService.save(competitionCommand).get().getEntityId();
    }

    @PatchMapping("/add/season")
    public String addSeason(@RequestBody AddSeason addSeason) throws ExecutionException, InterruptedException {
        return competitionService.addSeason(addSeason).get().getEntityId();
    }

    @PatchMapping("/add/stage")
    public String addStage(@RequestBody AddStage addStage) throws ExecutionException, InterruptedException {
        return competitionService.addStage(addStage).get().getEntityId();
    }

    @PatchMapping("/add/clubs")
    public String addClubs(@RequestBody AddClub addClub) throws ExecutionException, InterruptedException {
        return competitionService.addClubs(addClub).get().getEntityId();
    }

    @PatchMapping("/add/turns")
    public String addTurns(@RequestBody AddTurn addTurn) throws ExecutionException, InterruptedException {
        return competitionService.addTurns(addTurn).get().getEntityId();
    }

    @PatchMapping("/add/matches")
    public String addMatches(@RequestBody AddMatches addMatches) throws ExecutionException, InterruptedException {
        return competitionService.addMatches(addMatches).get().getEntityId();
    }

}
