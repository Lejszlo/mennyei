package com.sp.match.command.aggregator.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.sp.match.api.value.event.GameEvent;
import com.sp.match.command.aggregator.domain.MatchAggregator;
import com.sp.match.command.aggregator.infrastructure.MatchAggregateRepository;
import com.sp.match.api.command.AddMatchCommand;
import com.sp.match.api.command.PlayMatchCommand;
import com.sp.match.api.command.SetMatchCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.eventuate.EntityWithIdAndVersion;
import com.sp.match.api.value.MatchInfo;
import com.sp.match.api.value.lineup.LineUp;
import com.sp.organizer.api.value.club.AwayClubId;
import com.sp.organizer.api.value.club.HomeClubId;
import com.sp.organizer.api.value.competition.CompetitionId;
import com.sp.organizer.api.value.competition.StageId;

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

	public CompletableFuture<EntityWithIdAndVersion<MatchAggregator>> playMatch(HomeClubId homeClubId,
                                                                                AwayClubId awayClubId,
                                                                                CompetitionId competitionId,
																				StageId stageId,
																				String matchId,
                                                                                List<GameEvent> homeClubEvents,
                                                                                List<GameEvent> awayClubEvents) {
		PlayMatchCommand playMatchCommand = PlayMatchCommand.builder(competitionId, homeClubId, awayClubId, stageId)
				.homeClubevents(homeClubEvents)
				.awayClubevents(awayClubEvents)
				.build();
		return matchAggregateRepository.update(matchId, playMatchCommand);
	}


}
