package com.sp.organizer.command.aggregator.organizer.controller;

import com.sp.organizer.api.command.organizer.AddCompetitionOrganizerCommand;
import com.sp.organizer.api.command.organizer.CreateOrganizerCommand;
import com.sp.organizer.command.aggregator.organizer.service.OrganizerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/organizer/")
public class OrganizerCommandController {

    private final OrganizerService organizerService;

    @Autowired
    public OrganizerCommandController(OrganizerService organizerService) {
        this.organizerService = organizerService;
    }

    @PostMapping
    public void createOrganizer(@RequestBody CreateOrganizerCommand createOrganizerCommand) {
        organizerService.save(createOrganizerCommand);
    }

    @PostMapping("/{organizerId}/addCompetition")
    public void createOrganizer(@PathVariable("organizerId") String organizerId, @RequestBody AddCompetitionOrganizerCommand addCompetitionOrganizerCommand) {
        organizerService.addCompetition(addCompetitionOrganizerCommand, organizerId);
    }

}
