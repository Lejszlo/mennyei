package com.hajdu.sp.competition.update.service;

import com.hajdu.sp.competition.update.command.competition.*;
import com.hajdu.sp.competition.update.domain.CompetitionAggregate;
import com.hajdu.sp.competition.update.infrastructure.CompetitionAggregateRepository;
import io.eventuate.EntityWithIdAndVersion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;


@Service
public class CompetitionService {
	private CompetitionAggregateRepository competitionAggregateRepository;

	@Autowired
	public CompetitionService(CompetitionAggregateRepository competitionAggregateRepository) {
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

	public CompletableFuture<EntityWithIdAndVersion<CompetitionAggregate>> addClubs(AddClub addClub) {
		return competitionAggregateRepository.update(addClub.getStageId().getCompetitionId().getValue(), addClub);
	}

	public CompletableFuture<EntityWithIdAndVersion<CompetitionAggregate>> addTurns(AddTurn addTurn) {
		return competitionAggregateRepository.update(addTurn.getStageId().getCompetitionId().getValue(), addTurn);
	}

	public CompletableFuture<EntityWithIdAndVersion<CompetitionAggregate>> addMatches(AddMatches addMatches) {
		return competitionAggregateRepository.update(addMatches.getTurnId().getCompetitionId().getValue(), addMatches);
	}
}
