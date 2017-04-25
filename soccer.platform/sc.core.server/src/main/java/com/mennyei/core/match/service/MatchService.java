package com.mennyei.core.match.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mennyei.core.match.command.AddMatchCommand;
import com.mennyei.core.match.command.PlayMatchCommand;
import com.mennyei.core.match.command.SetMatchCommand;
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

	public CompletableFuture<EntityWithIdAndVersion<MatchAggregator>> preMatch(String matchId, List<LineUp> homeLineUps,
			List<LineUp> awayLineUps) {
		SetMatchCommand setMatchCommand = SetMatchCommand.builder().awayLineUps(awayLineUps).homeLineUps(homeLineUps).build();
		return matchAggregateRepository.update(matchId, setMatchCommand);
	}

	public CompletableFuture<EntityWithIdAndVersion<MatchAggregator>> playMatch(String matchId, List<MatchEvent> homeClubEvents, List<MatchEvent> awayClubEvents) {
		PlayMatchCommand playMatchCommand = PlayMatchCommand.builder().homeClubevents(homeClubEvents).awayClubevents(awayClubEvents).build();
		return matchAggregateRepository.update(matchId, playMatchCommand);
	}


}
