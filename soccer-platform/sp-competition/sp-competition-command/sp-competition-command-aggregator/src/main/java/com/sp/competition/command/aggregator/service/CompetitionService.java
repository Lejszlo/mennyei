package com.sp.competition.command.aggregator.service;

import com.sp.competition.command.aggregator.domain.CompetitionAggregate;
import com.sp.competition.command.aggregator.infrastructure.CompetitionAggregateRepository;
import io.eventuate.EntityWithIdAndVersion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sp.competition.api.command.AddClubs;
import sp.competition.api.command.AddSeason;
import sp.competition.api.command.AddStage;
import sp.competition.api.command.CreateCompetition;

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
