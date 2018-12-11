package com.sp.organizer.command.aggregator.competition.service;

import com.sp.organizer.api.command.competition.AddClubs;
import com.sp.organizer.api.command.competition.AddSeason;
import com.sp.organizer.api.command.competition.AddStage;
import com.sp.organizer.api.command.competition.CreateCompetition;
import com.sp.organizer.command.aggregator.competition.domain.CompetitionAggregate;
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

	public CompletableFuture<EntityWithIdAndVersion<CompetitionAggregate>> save(CreateCompetition createCompetition) {
		return competitionAggregateRepository.save(createCompetition);
	}

	public CompletableFuture<EntityWithIdAndVersion<CompetitionAggregate>> addSeason(AddSeason addSeason) {
		return competitionAggregateRepository.update(addSeason.getSeasonId().getCompetitionId().getValue(), addSeason);
	}

	public CompletableFuture<EntityWithIdAndVersion<CompetitionAggregate>> addStage(AddStage addStage) {
        return competitionAggregateRepository.update(addStage.getStageId().getCompetitionId().getValue(), addStage);
	}

	public CompletableFuture<EntityWithIdAndVersion<CompetitionAggregate>> addClubs(AddClubs addClubs) {
		return competitionAggregateRepository.update(addClubs.getStageId().getCompetitionId().getValue(), addClubs);
	}
}
