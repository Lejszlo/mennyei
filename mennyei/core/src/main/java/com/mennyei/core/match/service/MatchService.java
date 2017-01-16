package com.mennyei.core.match.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mennyei.core.competition.domain.CompetitionAggregator;
import com.mennyei.core.match.command.AddMatchCommand;
import com.mennyei.core.match.command.PlayMatchCommand;
import com.mennyei.core.match.domain.MatchAggregator;
import com.mennyei.core.match.domain.MatchInfo;
import com.mennyei.core.match.domain.event.MatchEvent;
import com.mennyei.core.match.domain.event.lineup.LineUp;
import com.mennyei.core.match.infrastructure.MatchAggregateRepository;

import io.eventuate.EntityWithIdAndVersion;

@Service
public class MatchService {
	
	@Autowired
	private MatchAggregateRepository matchAggregateRepository;
	
	public CompletableFuture<EntityWithIdAndVersion<MatchAggregator>> addMatch(MatchInfo matchInfo) {
		AddMatchCommand addMatchCommand = AddMatchCommand.builder(matchInfo).build();
		return matchAggregateRepository.save(addMatchCommand);
	}


	public CompletableFuture<EntityWithIdAndVersion<MatchAggregator>> playMatch(String matchId, List<MatchEvent> homeClubEvents, List<MatchEvent> awayClubEvents) {
		PlayMatchCommand playMatchCommand = PlayMatchCommand.builder().homeClubevents(homeClubEvents).awayClubevents(awayClubEvents).build();
		return matchAggregateRepository.update(matchId, playMatchCommand);
	}


	public CompletableFuture<EntityWithIdAndVersion<CompetitionAggregator>> preMatch(String entityId, List<LineUp> homeLineUps,
			List<LineUp> awayLineUps) {
		return null;
	}
}
