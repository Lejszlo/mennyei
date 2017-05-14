package com.mennyei.util.player.service;


import com.sp.core.backend.value.match.MatchResultDetailes;
import com.sp.core.backend.value.match.event.CardEvent;
import com.sp.core.backend.value.match.event.GoalEvent;
import com.sp.core.backend.value.match.event.MatchEventType;
import com.sp.core.backend.value.match.event.SubstitutionEvent;
import com.sp.core.backend.value.match.lineup.LineUpType;
import com.mennyei.util.player.domain.PlayerMatchStatisticData;
import com.mennyei.util.player.domain.PlayerQuery;
import com.mennyei.util.player.domain.lineup.LineUpQuery;
import com.mennyei.util.player.infrastructure.LineUpQueryMongoRepository;
import com.mennyei.util.player.infrastructure.PlayerQueryMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PlayerMatchStatisticService {
	
	@Autowired
	private PlayerQueryMongoRepository playerQueryMongoRepository;

	@Autowired
	private LineUpQueryMongoRepository lineUpQueryMongoRepository;
	
	public void updatePlayerStatistics(String matchId, MatchResultDetailes matchResultDetailes, String competitionId) {
        lineUpQueryMongoRepository.findByMatchId(matchId).stream().forEach(lineUp -> process(lineUp, matchResultDetailes, competitionId));
	}
	
	public void process(LineUpQuery lineUpQuery, MatchResultDetailes matchResultDetailes, String competitionId) {
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

		List<SubstitutionEvent> substitutionIn = matchResultDetailes.getSubstitutionInEventForPlayer(playerQuery.getId());
		if(!substitutionIn.isEmpty()) {
			playerMatchStatisticData.incraseSubstitutionIn(1);
		}

		List<SubstitutionEvent> substitutionOut = matchResultDetailes.getSubstitutionOutEventForPlayer(playerQuery.getId());
		if(!substitutionOut.isEmpty()) {
			playerMatchStatisticData.incraseSubstitutionOut(1);
		}

		playerMatchStatisticData.incrasePlayedMinute(calculatePlayedMinutes(lineUpQuery, substitutionIn, substitutionOut));

		playerQueryMongoRepository.save(playerQuery);
	}

	//TODO calculate total match time
	private int calculatePlayedMinutes(LineUpQuery lineUpQuery, List<SubstitutionEvent> substitutionIn, List<SubstitutionEvent> substitutionOut) {
		if(LineUpType.SUBSTITUTION.equals(lineUpQuery.getLineUpType()) && !substitutionIn.isEmpty()) {
			SubstitutionEvent substitutionEvent = substitutionIn.get(0);
			return substitutionEvent.getMinute();
		}

		if(LineUpType.STARTER.equals(lineUpQuery.getLineUpType())) {
			if(substitutionOut.isEmpty()) {
				return 90;
			}

			SubstitutionEvent substitutionEvent = substitutionOut.get(0);
			return 90 - substitutionEvent.getMinute();
		}
		return 0;
	}

	private PlayerMatchStatisticData getPlayerMatchStatisticData(MatchResultDetailes matchResultDetailes, PlayerQuery playerQuery, String competitionId) {
		PlayerMatchStatisticData playerMatchStatisticData = playerQuery.getPlayerMatchStatisticDatas().get(competitionId);

		if(playerMatchStatisticData == null) {
			playerMatchStatisticData = new PlayerMatchStatisticData();
			playerQuery.getPlayerMatchStatisticDatas().put(competitionId, playerMatchStatisticData);
		}
		return playerMatchStatisticData;
	}
	
}
