package com.mennyei.core.competition.service;

import com.mennyei.core.competition.commands.AddCompetitionCommand;
import com.mennyei.core.competition.commands.RegisterClubCommand;
import com.mennyei.core.competition.domain.CompetitionAggregator;
import com.mennyei.core.competition.domain.CompetitionInfo;
import com.mennyei.core.competition.domain.rule.CompetitionRuleSet;
import com.mennyei.core.competition.domain.season.Stage;
import com.mennyei.core.competition.infrastructure.CompetitionAggregateRepository;
import io.eventuate.EntityWithIdAndVersion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class CompetitionService {

	private CompetitionAggregateRepository competitionAggregateRepository;

	@Autowired
	CompetitionService(CompetitionAggregateRepository competitionAggregateRepository) {
		this.competitionAggregateRepository = competitionAggregateRepository;
	}

	public CompletableFuture<EntityWithIdAndVersion<CompetitionAggregator>> addCompetition(CompetitionInfo competition,
			CompetitionRuleSet competitionRules, Stage stage) {
		return competitionAggregateRepository
				.save(AddCompetitionCommand.builder().competitionInfo(competition).competitionRules(competitionRules).stage(stage).build());
	}

	public CompletableFuture<EntityWithIdAndVersion<CompetitionAggregator>> registerClubsToCompetition(String competitionId, String... clubId) {
		RegisterClubCommand registerClubCommand = new RegisterClubCommand(Arrays.stream(clubId).collect(Collectors.toSet()));
		return competitionAggregateRepository.update(competitionId, registerClubCommand);
	}
	

}
