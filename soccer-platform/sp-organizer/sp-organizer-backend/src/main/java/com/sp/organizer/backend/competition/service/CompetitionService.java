package com.sp.organizer.backend.competition.service;

import com.sp.organizer.backend.competition.domain.Competition;
import com.sp.organizer.backend.competition.domain.CompetitionAggregate;
import com.sp.organizer.backend.competition.domain.CompetitionInfo;
import com.sp.organizer.backend.competition.domain.rule.CompetitionRuleSet;
import com.sp.organizer.backend.competition.domain.season.Stage;
import com.sp.organizer.backend.competition.command.AddCompetitionCommand;
import com.sp.organizer.backend.competition.command.RegisterClubCommand;
import com.sp.organizer.backend.competition.infrastructure.CompetitionAggregateRepository;
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

	public CompletableFuture<EntityWithIdAndVersion<CompetitionAggregate>> addCompetition(CompetitionInfo competitionInfo,
																				 CompetitionRuleSet competitionRules, Stage stage) {
		return competitionAggregateRepository
				.save(AddCompetitionCommand.builder().competitionInfo(competitionInfo).competitionRules(competitionRules).stage(stage).build());
	}

	public CompletableFuture<EntityWithIdAndVersion<CompetitionAggregate>> registerClubsToCompetition(String competitionId, String... clubId) {
		RegisterClubCommand registerClubCommand = new RegisterClubCommand(Arrays.stream(clubId).collect(Collectors.toSet()));
		return competitionAggregateRepository.update(competitionId, registerClubCommand);
	}
	

}
