package com.sp.organizer.command.aggregator.competition.controller;

import com.sp.organizer.api.command.competition.CreateCompetitionCommand;
import com.sp.organizer.command.aggregator.competition.service.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/competition/")
@RestController
public class CompetitionController {

    private CompetitionService competitionService;

    @Autowired
    public CompetitionController(CompetitionService competitionService) {
        this.competitionService = competitionService;
    }

    @RequestMapping(method = RequestMethod.PUT)
    public String registerCompetition(@RequestBody CreateCompetitionCommand competitionCommand) {
        return competitionService.save(competitionCommand).toString();
    }


}
