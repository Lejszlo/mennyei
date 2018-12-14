package com.sp.match.command.aggregator.controller;

import com.sp.match.api.command.CreateMatch;
import com.sp.match.command.aggregator.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RequestMapping("/match")
@RestController
public class MatchCommandController {

    private MatchService matchService;

    @Autowired
    public MatchCommandController(MatchService matchService) {
        this.matchService = matchService;
    }

    @PostMapping
    public String createMatch(@RequestBody CreateMatch createMatch) throws ExecutionException, InterruptedException {
        return matchService.createMatch(createMatch).get().getEntityId();
    }
}
