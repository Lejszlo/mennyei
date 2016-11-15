package com.mennyei.core.competition.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mennyei.core.competition.commands.RegisterCompetitionCommand;
import com.mennyei.core.competition.domain.Competition;
import com.mennyei.core.competition.domain.CompetitionAggregator;
import com.mennyei.core.competition.infrastructure.CompetitionRepository;

import io.eventuate.EntityWithIdAndVersion;

@Service
public class CompetitionService {

	@Autowired
	private CompetitionRepository competitionRepository;

	public CompletableFuture<EntityWithIdAndVersion<CompetitionAggregator>> registerCompetition(Competition competition) {
		return competitionRepository.save(RegisterCompetitionCommand.builder().competition(competition).build());
	}
	
}
