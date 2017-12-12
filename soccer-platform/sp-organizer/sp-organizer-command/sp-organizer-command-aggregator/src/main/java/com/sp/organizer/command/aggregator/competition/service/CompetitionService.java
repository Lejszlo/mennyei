package com.sp.organizer.command.aggregator.competition.service;

import com.sp.organizer.command.aggregator.competition.domain.CompetitionAggregate;
import com.sp.core.backend.web.resource.IdResource;
import command.competition.AddStageCommand;
import command.competition.SaveCompetitionCommand;
import com.sp.organizer.command.aggregator.competition.infrastructure.CompetitionAggregateRepository;
import io.eventuate.EntityWithIdAndVersion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import value.competition.season.Stage;

import java.util.concurrent.CompletableFuture;

@Service
public class CompetitionService {
	private static final Logger LOGGER = LoggerFactory.getLogger(CompetitionService.class);

	private CompetitionAggregateRepository competitionAggregateRepository;

	@Autowired
	CompetitionService(CompetitionAggregateRepository competitionAggregateRepository) {
		this.competitionAggregateRepository = competitionAggregateRepository;
	}

	public IdResource save(SaveCompetitionCommand saveCompetitionCommand) {
		CompletableFuture<EntityWithIdAndVersion<CompetitionAggregate>> competitionAggregate = competitionAggregateRepository.save(saveCompetitionCommand);
		EntityWithIdAndVersion<CompetitionAggregate> competition = null;
		try {
			competition = competitionAggregate.get();
		} catch (Exception exception) {
			LOGGER.error("Error: ", exception);
		}
		return new IdResource(competition.getEntityId());
	}

	public IdResource addStage(Stage stage, IdResource competitionId) {
        CompletableFuture<EntityWithIdAndVersion<CompetitionAggregate>> updated = competitionAggregateRepository.update(competitionId.getId(), new AddStageCommand(stage));
        EntityWithIdAndVersion<CompetitionAggregate> competition = null;
        try {
            competition = updated.get();
        } catch (Exception exception) {
            LOGGER.error("Error: ", exception);
        }
        return new IdResource(competition.getEntityId());
	}

}
