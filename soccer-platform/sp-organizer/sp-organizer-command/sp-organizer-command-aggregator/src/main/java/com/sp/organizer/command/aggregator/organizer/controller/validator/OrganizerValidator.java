package com.sp.organizer.command.aggregator.organizer.controller.validator;

import com.sp.core.backend.exception.ValidationException;
import com.sp.organizer.api.command.organizer.AddCompetitionOrganizerCommand;
import com.sp.organizer.api.value.competition.CompetitionId;
import com.sp.organizer.command.aggregator.competition.infrastructure.CompetitionAggregateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Service
public class OrganizerValidator {

    private CompetitionAggregateRepository competitionAggregateRepository;

    @Autowired
    public OrganizerValidator(CompetitionAggregateRepository competitionAggregateRepository) {
        this.competitionAggregateRepository = competitionAggregateRepository;
    }

    public void validateAddCompetition(AddCompetitionOrganizerCommand addCompetitionOrganizerCommand) {
        CompetitionId competitionId = addCompetitionOrganizerCommand.getCompetitionId();

        try {
            Optional.ofNullable(competitionAggregateRepository.find(competitionId.getValue()).get())
                    .orElseThrow(ValidationException::new);
        } catch (InterruptedException | ExecutionException e) {
            throw new ValidationException();
        }

    }


}
