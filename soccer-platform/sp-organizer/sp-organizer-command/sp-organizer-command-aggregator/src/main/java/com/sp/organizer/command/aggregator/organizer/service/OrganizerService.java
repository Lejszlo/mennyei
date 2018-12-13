package com.sp.organizer.command.aggregator.organizer.service;

import com.sp.organizer.api.command.AddCompetitionOrganizerCommand;
import com.sp.organizer.api.command.CreateOrganizerCommand;
import com.sp.organizer.command.aggregator.organizer.domain.OrganizerAggregate;
import com.sp.organizer.command.aggregator.organizer.insfrastructure.OrganizerAggregateRepository;
import io.eventuate.EntityWithIdAndVersion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class OrganizerService {

    private OrganizerAggregateRepository organizerAggregateRepository;

    @Autowired
    public OrganizerService(OrganizerAggregateRepository organizerAggregateRepository) {
        this.organizerAggregateRepository = organizerAggregateRepository;
    }

    public CompletableFuture<EntityWithIdAndVersion<OrganizerAggregate>> save(CreateOrganizerCommand createOrganizerCommand) {
        return organizerAggregateRepository.save(createOrganizerCommand);
    }

    public CompletableFuture<EntityWithIdAndVersion<OrganizerAggregate>> addCompetition(AddCompetitionOrganizerCommand addStageCommand, String organizerId) {
        return organizerAggregateRepository.update(organizerId, addStageCommand);
    }

}
