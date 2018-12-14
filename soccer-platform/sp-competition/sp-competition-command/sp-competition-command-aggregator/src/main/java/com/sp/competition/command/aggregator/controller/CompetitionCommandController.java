package com.sp.competition.command.aggregator.controller;

import com.sp.competition.command.aggregator.service.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sp.competition.api.command.*;

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

    @PostMapping("/add/season")
    public String addSeason(@RequestBody AddSeason addSeason) throws ExecutionException, InterruptedException {
        return competitionService.addSeason(addSeason).get().getEntityId();
    }

    @PostMapping("/add/stage")
    public String addStage(@RequestBody AddStage addStage) throws ExecutionException, InterruptedException {
        return competitionService.addStage(addStage).get().getEntityId();
    }

    @PostMapping("/add/clubs")
    public String addClubs(@RequestBody AddClubs addClubs) throws ExecutionException, InterruptedException {
        return competitionService.addClubs(addClubs).get().getEntityId();
    }

    @PostMapping("/add/turns")
    public String addTurns(@RequestBody AddTurns addTurns) throws ExecutionException, InterruptedException {
        return competitionService.addTurns(addTurns).get().getEntityId();
    }

    @PostMapping("/add/matches")
    public String addMatches(@RequestBody AddMatches addMatches) throws ExecutionException, InterruptedException {
        return competitionService.addMatches(addMatches).get().getEntityId();
    }

}
