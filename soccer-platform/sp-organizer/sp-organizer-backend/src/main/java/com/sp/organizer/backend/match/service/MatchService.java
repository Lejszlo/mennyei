package com.sp.organizer.backend.match.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.sp.organizer.backend.match.command.AddMatchCommand;
import com.sp.organizer.backend.match.command.PlayMatchCommand;
import com.sp.organizer.backend.match.command.SetMatchCommand;
import com.sp.organizer.backend.match.domain.MatchAggregator;
import com.sp.core.backend.value.match.MatchInfo;
import com.sp.core.backend.value.match.event.MatchEvent;
import com.sp.core.backend.value.match.lineup.LineUp;
import com.sp.organizer.backend.match.infrastructure.MatchAggregateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	public CompletableFuture<EntityWithIdAndVersion<MatchAggregator>> playMatch(String competitionId, String matchId, List<MatchEvent> homeClubEvents, List<MatchEvent> awayClubEvents) {
		PlayMatchCommand playMatchCommand = PlayMatchCommand.builder(competitionId).homeClubevents(homeClubEvents).awayClubevents(awayClubEvents).build();
		return matchAggregateRepository.update(matchId, playMatchCommand);
	}


}
