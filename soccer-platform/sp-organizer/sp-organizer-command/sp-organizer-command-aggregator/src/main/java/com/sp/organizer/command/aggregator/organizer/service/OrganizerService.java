package com.sp.organizer.command.aggregator.organizer.service;

import com.sp.organizer.api.command.organizer.AddCompetitionOrganizerCommand;
import com.sp.organizer.api.command.organizer.CreateOrganizerCommand;
import com.sp.organizer.command.aggregator.organizer.controller.validator.OrganizerValidator;
import com.sp.organizer.command.aggregator.organizer.domain.OrganizerAggregate;
import com.sp.organizer.command.aggregator.organizer.insfrastructure.OrganizerAggregateRepository;
import io.eventuate.EntityWithIdAndVersion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class OrganizerService {

    private OrganizerAggregateRepository organizerAggregateRepository;
    private OrganizerValidator organizerValidator;

    @Autowired
    public OrganizerService(OrganizerAggregateRepository organizerAggregateRepository,
                            OrganizerValidator organizerValidator) {
        this.organizerAggregateRepository = organizerAggregateRepository;
        this.organizerValidator = organizerValidator;
    }

    public CompletableFuture<EntityWithIdAndVersion<OrganizerAggregate>> save(CreateOrganizerCommand createOrganizerCommand) {
        return organizerAggregateRepository.save(createOrganizerCommand);
    }

    public CompletableFuture<EntityWithIdAndVersion<OrganizerAggregate>> addCompetition(AddCompetitionOrganizerCommand addStageCommand, String organizerId) {
        organizerValidator.validateAddCompetition(addStageCommand);

        return organizerAggregateRepository.update(organizerId, addStageCommand);
    }

}
