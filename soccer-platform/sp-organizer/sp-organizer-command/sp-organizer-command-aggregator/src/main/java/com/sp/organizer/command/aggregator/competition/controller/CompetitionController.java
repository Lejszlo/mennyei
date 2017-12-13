package com.sp.organizer.command.aggregator.competition.controller;

import com.sp.core.backend.web.resource.IdResource;
import command.competition.SaveCompetitionCommand;
import com.sp.organizer.command.aggregator.competition.service.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/competition/")
@RestController
public class CompetitionController {

    @Autowired
    private CompetitionService competitionService;

    @RequestMapping(method = RequestMethod.PUT)
    public IdResource registerCompetition(@RequestBody SaveCompetitionCommand competitionCommand) {
        return competitionService.save(competitionCommand);
    }
}
