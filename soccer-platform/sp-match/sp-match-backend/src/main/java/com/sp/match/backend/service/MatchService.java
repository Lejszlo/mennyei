package com.sp.match.backend.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import sp.match.api.command.AddMatchCommand;
import sp.match.api.command.PlayMatchCommand;
import sp.match.api.command.SetMatchCommand;
import com.sp.match.backend.domain.MatchAggregator;
import com.sp.match.backend.infrastucture.MatchAggregateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.eventuate.EntityWithIdAndVersion;
import sp.match.api.value.MatchInfo;
import sp.match.api.value.event.MatchEvent;
import sp.match.api.value.lineup.LineUp;

@Service
public class MatchService {
	
	private final MatchAggregateRepository matchAggregateRepository;

	@Autowired
	public MatchService(MatchAggregateRepository matchAggregateRepository) {
		this.matchAggregateRepository = matchAggregateRepository;
	}

	public CompletableFuture<EntityWithIdAndVersion<MatchAggregator>> addMatch(MatchInfo matchInfo) {
		AddMatchCommand addMatchCommand = AddMatchCommand.builder(matchInfo).build();
		return matchAggregateRepository.save(addMatchCommand);
	}

	public CompletableFuture<EntityWithIdAndVersion<MatchAggregator>> preMatch(String matchId, List<LineUp> homeLineUps,
			List<LineUp> awayLineUps) {
		SetMatchCommand setMatchCommand = SetMatchCommand.builder().awayLineUps(awayLineUps).homeLineUps(homeLineUps).build();
		return matchAggregateRepository.update(matchId, setMatchCommand);
	}

	public CompletableFuture<EntityWithIdAndVersion<MatchAggregator>> playMatch(String competitionId, String matchId, List<MatchEvent> homeClubEvents, List<MatchEvent> awayClubEvents) {
		PlayMatchCommand playMatchCommand = PlayMatchCommand.builder(competitionId).homeClubevents(homeClubEvents).awayClubevents(awayClubEvents).build();
		return matchAggregateRepository.update(matchId, playMatchCommand);
	}


}
