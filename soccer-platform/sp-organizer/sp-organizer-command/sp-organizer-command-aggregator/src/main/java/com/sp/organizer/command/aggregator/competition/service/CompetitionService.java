package com.sp.organizer.command.aggregator.competition.service;

import com.sp.organizer.api.command.competition.AddClubsToStageCommand;
import com.sp.organizer.command.aggregator.competition.domain.CompetitionAggregate;
import com.sp.core.backend.web.resource.IdResource;
import com.sp.organizer.api.command.competition.AddStageCommand;
import com.sp.organizer.api.command.competition.SaveCompetitionCommand;
import com.sp.organizer.command.aggregator.competition.infrastructure.CompetitionAggregateRepository;
import io.eventuate.EntityWithIdAndVersion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sp.organizer.api.value.competition.season.Stage;

import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class CompetitionService {
	private static final Logger LOGGER = LoggerFactory.getLogger(CompetitionService.class);

	private CompetitionAggregateRepository competitionAggregateRepository;

	@Autowired
	CompetitionService(CompetitionAggregateRepository competitionAggregateRepository) {
		this.competitionAggregateRepository = competitionAggregateRepository;
	}

	public String save(SaveCompetitionCommand saveCompetitionCommand) {
		CompletableFuture<EntityWithIdAndVersion<CompetitionAggregate>> competitionAggregate = competitionAggregateRepository.save(saveCompetitionCommand);
		EntityWithIdAndVersion<CompetitionAggregate> competition = null;
		try {

			competition = competitionAggregate.get();
		} catch (Exception exception) {
			LOGGER.error("Error: ", exception);
		}
		return competition.getEntityId();
	}

	public void addStage(Stage stage, String competitionId) {
        CompletableFuture<EntityWithIdAndVersion<CompetitionAggregate>> updated = competitionAggregateRepository.update(competitionId, new AddStageCommand(stage));
        try {
            updated.get();
        } catch (Exception exception) {
            LOGGER.error("Error: ", exception);
        }
	}

	public void addClubToStage(IdResource competitionId, UUID stageId, Collection<UUID> clubIds) {
		CompletableFuture<EntityWithIdAndVersion<CompetitionAggregate>> updated = competitionAggregateRepository.update(competitionId.toString(), AddClubsToStageCommand.builder()
				.clubIds(clubIds)
				.stageId(stageId)
				.build());
		try {
			updated.get();
		} catch (Exception exception) {
			LOGGER.error("Error: ", exception);
		}
	}

}
