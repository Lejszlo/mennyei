package com.sp.organizer.command.aggregator.competition.service;

import com.sp.organizer.command.aggregator.competition.domain.CompetitionAggregate;
import com.sp.organizer.api.command.competition.AddStageCompetitionCommand;
import com.sp.organizer.api.command.competition.CreateCompetitionCommand;
import com.sp.organizer.command.aggregator.competition.infrastructure.CompetitionAggregateRepository;
import io.eventuate.EntityWithIdAndVersion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.concurrent.CompletableFuture;

@Service
public class CompetitionService {
	private CompetitionAggregateRepository competitionAggregateRepository;

	@Autowired
	CompetitionService(CompetitionAggregateRepository competitionAggregateRepository) {
		this.competitionAggregateRepository = competitionAggregateRepository;
	}

	public CompletableFuture<EntityWithIdAndVersion<CompetitionAggregate>> save(CreateCompetitionCommand createCompetitionCommand) {
		return competitionAggregateRepository.save(createCompetitionCommand);
	}

	public CompletableFuture<EntityWithIdAndVersion<CompetitionAggregate>> addStage(AddStageCompetitionCommand addStageCommand, String competitionId) {
        return competitionAggregateRepository.update(competitionId, addStageCommand);
	}

}
