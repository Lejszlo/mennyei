package com.sp.match.command.aggregator.service;

import com.sp.match.api.command.AddMatchCommand;
import com.sp.match.api.command.SetMatchCommand;
import com.sp.match.api.value.MatchInfo;
import com.sp.match.api.value.lineup.LineUp;
import com.sp.match.command.aggregator.domain.MatchAggregator;
import com.sp.match.command.aggregator.infrastructure.MatchAggregateRepository;
import io.eventuate.EntityWithIdAndVersion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

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
		SetMatchCommand setMatchCommand = SetMatchCommand.builder()
                .awayLineUps(awayLineUps)
                .homeLineUps(homeLineUps)
                .build();
		return matchAggregateRepository.update(matchId, setMatchCommand);
	}
}
