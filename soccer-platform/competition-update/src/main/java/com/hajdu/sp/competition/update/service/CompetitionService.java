package com.hajdu.sp.competition.update.service;

import com.hajdu.sp.competition.update.command.competition.*;
import com.hajdu.sp.competition.update.infrastructure.CompetitionAggregateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


@Service
public class CompetitionService {
	private CompetitionAggregateRepository competitionAggregateRepository;

	@Autowired
	public CompetitionService(CompetitionAggregateRepository competitionAggregateRepository) {
		this.competitionAggregateRepository = competitionAggregateRepository;
	}

	public String save(CreateCompetition createCompetition) throws ExecutionException, InterruptedException {
		return competitionAggregateRepository.save(createCompetition)
				.get()
				.getEntityId();
	}

	public String addSeason(String id, AddSeason addSeason) throws ExecutionException, InterruptedException {
		return competitionAggregateRepository.update(id, addSeason)
				.get()
				.getEntityId();
	}

	public String addStages(String id, List<AddStage> addStages) throws ExecutionException, InterruptedException {
		CompletableFuture.allOf(convert(id, addStages)).get();
        return id;
	}

	public String addTurns(String id, List<AddTurn> addTurns) throws ExecutionException, InterruptedException {
		CompletableFuture.allOf(convert(id, addTurns)).get();
		return id;
	}

	public String addMatches(String id, List<AddMatch> addMatches) throws ExecutionException, InterruptedException {
		CompletableFuture.allOf(convert(id, addMatches)).get();
		return id;
	}

	public String addClubs(String id, List<AddClub> addClubs) throws ExecutionException, InterruptedException {
		CompletableFuture.allOf(convert(id, addClubs)).get();
		return id;
	}

	private <T> CompletableFuture[] convert(String id, List<T> commands) {
		return commands.stream().map(command -> competitionAggregateRepository
				.update(id, (CompetitionCommand) command))
				.toArray(CompletableFuture[]::new);
	}
}
