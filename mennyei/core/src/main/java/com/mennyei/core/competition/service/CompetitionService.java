package com.mennyei.core.competition.service;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mennyei.core.competition.commands.AddCompetitionCommand;
import com.mennyei.core.competition.commands.RegisterClubCommand;
import com.mennyei.core.competition.domain.CompetitionAggregator;
import com.mennyei.core.competition.domain.CompetitionInfo;
import com.mennyei.core.competition.domain.rule.CompetitionRules;
import com.mennyei.core.competition.infrastructure.CompetitionAggregateRepository;

import io.eventuate.EntityWithIdAndVersion;

@Service
public class CompetitionService {

	@Autowired
	private CompetitionAggregateRepository competitionRepository;

	public CompletableFuture<EntityWithIdAndVersion<CompetitionAggregator>> addCompetition(CompetitionInfo competition, CompetitionRules competitionRules) {
		return competitionRepository.save(AddCompetitionCommand.builder().competition(competition).competitionRules(competitionRules).build());
	}

	public CompletableFuture<EntityWithIdAndVersion<CompetitionAggregator>> registerClubToCompetition(String competitionId, String... clubId) {
		RegisterClubCommand registerClubCommand = RegisterClubCommand.builder().clubIds(Arrays.stream(clubId).collect(Collectors.toSet())).build();
		return competitionRepository.update(competitionId, registerClubCommand);
	}
	
}
