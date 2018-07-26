package com.sp.player.query.player.service;


import com.sp.player.query.player.domain.PlayerMatchStatisticData;
import com.sp.player.query.player.domain.PlayerQuery;
import com.sp.player.query.player.domain.lineup.LineUpQuery;
import com.sp.player.query.player.infrastructure.LineUpQueryMongoRepository;
import com.sp.player.query.player.infrastructure.PlayerQueryMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sp.match.api.value.MatchResultDetails;
import com.sp.match.api.value.event.CardEvent;
import com.sp.match.api.value.event.GoalEvent;
import com.sp.match.api.value.event.MatchEventType;
import com.sp.match.api.value.event.SubstitutionGameEvent;
import com.sp.match.api.value.lineup.LineUpType;
import com.sp.organizer.api.value.competition.CompetitionId;

import java.util.List;


@Service
public class PlayerMatchStatisticService {
	
	private final PlayerQueryMongoRepository playerQueryMongoRepository;

	private final LineUpQueryMongoRepository lineUpQueryMongoRepository;

	@Autowired
	public PlayerMatchStatisticService(PlayerQueryMongoRepository playerQueryMongoRepository, LineUpQueryMongoRepository lineUpQueryMongoRepository) {
		this.playerQueryMongoRepository = playerQueryMongoRepository;
		this.lineUpQueryMongoRepository = lineUpQueryMongoRepository;
	}

	public void updatePlayerStatistics(String matchId, MatchResultDetails matchResultDetailes, CompetitionId competitionId) {
        lineUpQueryMongoRepository.findByMatchId(matchId).forEach(lineUp -> process(lineUp, matchResultDetailes, competitionId));
	}
	
	public void process(LineUpQuery lineUpQuery, MatchResultDetails matchResultDetailes, CompetitionId competitionId) {
		PlayerQuery playerQuery = lineUpQuery.getPlayerQuery();

		PlayerMatchStatisticData playerMatchStatisticData = getPlayerMatchStatisticData(matchResultDetailes, playerQuery, competitionId);

		boolean isNamedForMatch = false;
		if(LineUpType.STARTER.equals(lineUpQuery.getLineUpType())) {
			playerMatchStatisticData.incraseStarter(1);
			isNamedForMatch = true;
		}
		if(LineUpType.SUBSTITUTION.equals(lineUpQuery.getLineUpType())) {
			playerMatchStatisticData.incraseSubtitution(1);
			isNamedForMatch = true;
		}

		if(isNamedForMatch) {
			playerMatchStatisticData.incraseTotalMatch(1);
		}

		List<GoalEvent> goals = matchResultDetailes.getGoalEventsForPlayer(playerQuery.getId(), MatchEventType.GOAL);
		playerMatchStatisticData.incraseScoredGoalAmount(goals.size());

		List<GoalEvent> ownGoals = matchResultDetailes.getGoalEventsForPlayer(playerQuery.getId(), MatchEventType.OWN_GOAL);
		playerMatchStatisticData.incraseScoredGoalAmount(ownGoals.size());

		List<CardEvent> redCards = matchResultDetailes.getCardEventsForPlayer(playerQuery.getId(), MatchEventType.RED_CARD);
		playerMatchStatisticData.incraseScoredGoalAmount(redCards.size());

		List<CardEvent> yellowCards = matchResultDetailes.getCardEventsForPlayer(playerQuery.getId(), MatchEventType.YELLOW_CARD);
		playerMatchStatisticData.incraseScoredGoalAmount(yellowCards.size());

		List<SubstitutionGameEvent> substitutionIn = matchResultDetailes.getSubstitutionInEventForPlayer(playerQuery.getId());
		if(!substitutionIn.isEmpty()) {
			playerMatchStatisticData.incraseSubstitutionIn(1);
		}

		List<SubstitutionGameEvent> substitutionOut = matchResultDetailes.getSubstitutionOutEventForPlayer(playerQuery.getId());
		if(!substitutionOut.isEmpty()) {
			playerMatchStatisticData.incraseSubstitutionOut(1);
		}

		playerMatchStatisticData.incrasePlayedMinute(calculatePlayedMinutes(lineUpQuery, substitutionIn, substitutionOut));

		playerQueryMongoRepository.save(playerQuery);
	}

	//TODO calculate total match time
	private int calculatePlayedMinutes(LineUpQuery lineUpQuery, List<SubstitutionGameEvent> substitutionIn, List<SubstitutionGameEvent> substitutionOut) {
		if(LineUpType.SUBSTITUTION.equals(lineUpQuery.getLineUpType()) && !substitutionIn.isEmpty()) {
			SubstitutionGameEvent substitutionGameEvent = substitutionIn.get(0);
			return substitutionGameEvent.getMinute();
		}

		if(LineUpType.STARTER.equals(lineUpQuery.getLineUpType())) {
			if(substitutionOut.isEmpty()) {
				return 90;
			}

			SubstitutionGameEvent substitutionGameEvent = substitutionOut.get(0);
			return 90 - substitutionGameEvent.getMinute();
		}
		return 0;
	}

	private PlayerMatchStatisticData getPlayerMatchStatisticData(MatchResultDetails matchResultDetailes, PlayerQuery playerQuery, CompetitionId competitionId) {
		PlayerMatchStatisticData playerMatchStatisticData = playerQuery.getPlayerMatchStatisticDatas().get(competitionId);

		if(playerMatchStatisticData == null) {
			playerMatchStatisticData = new PlayerMatchStatisticData();
			playerQuery.getPlayerMatchStatisticDatas().put(competitionId, playerMatchStatisticData);
		}
		return playerMatchStatisticData;
	}
	
}
