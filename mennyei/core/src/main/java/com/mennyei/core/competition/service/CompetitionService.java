package com.mennyei.core.competition.service;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mennyei.core.competition.commands.AddCompetitionCommand;
import com.mennyei.core.competition.commands.AddMatchCommand;
import com.mennyei.core.competition.commands.FillMatchCommand;
import com.mennyei.core.competition.commands.RegisterClubCommand;
import com.mennyei.core.competition.domain.CompetitionAggregator;
import com.mennyei.core.competition.domain.CompetitionInfo;
import com.mennyei.core.competition.domain.match.domain.Match;
import com.mennyei.core.competition.domain.match.domain.match.event.MatchEvent;
import com.mennyei.core.competition.domain.rule.CompetitionRules;
import com.mennyei.core.competition.domain.season.Stage;
import com.mennyei.core.competition.infrastructure.CompetitionAggregateRepository;

import io.eventuate.EntityWithIdAndVersion;

@Service
public class CompetitionService {

	@Autowired
	private CompetitionAggregateRepository competitionRepository;

	public CompletableFuture<EntityWithIdAndVersion<CompetitionAggregator>> addCompetition(CompetitionInfo competition, CompetitionRules competitionRules, Stage stage) {
		return competitionRepository.save(AddCompetitionCommand.builder().competition(competition).competitionRules(competitionRules).stage(stage).build());
	}

	public CompletableFuture<EntityWithIdAndVersion<CompetitionAggregator>> registerClubToCompetition(String competitionId, String... clubId) {
		RegisterClubCommand registerClubCommand = RegisterClubCommand.builder().clubIds(Arrays.stream(clubId).collect(Collectors.toSet())).build();
		return competitionRepository.update(competitionId, registerClubCommand);
	}
	
	public CompletableFuture<EntityWithIdAndVersion<CompetitionAggregator>> addMatch(String competitionId, String stageName, int turnIndex, List<Match> matches) {
		AddMatchCommand addMatchCommand = AddMatchCommand.builder(competitionId, stageName, turnIndex).matches(matches).build();
		return competitionRepository.update(competitionId, addMatchCommand);
	}
	
	public CompletableFuture<EntityWithIdAndVersion<CompetitionAggregator>> fillMatchWithEvents(String competitionId, String stageName, int turnIndex, String homeClubId, List<MatchEvent> events) {
		FillMatchCommand fillMatchCommand = FillMatchCommand.builder(competitionId, stageName, turnIndex, homeClubId).events(events).build();
		return competitionRepository.update(competitionId, fillMatchCommand);
	}
	
}
