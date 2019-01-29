package com.hajdu.sp.club.update.aggregator.controller;

import com.hajdu.sp.club.lib.command.CreateClub;
import com.hajdu.sp.club.update.aggregator.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RequestMapping("/club")
@RestController
public class ClubCommandController {

    private ClubService clubService;

    @Autowired
    public ClubCommandController(ClubService clubService) {
        this.clubService = clubService;
    }

    @PostMapping
    public String createCompetition(@RequestBody CreateClub createClub) throws ExecutionException, InterruptedException {
        return clubService.saveClub(createClub).get().getEntityId();
    }
}
