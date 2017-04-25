package com.mennyei.publicweb.club.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mennyei.core.match.domain.event.CardEvent;
import com.mennyei.core.match.domain.event.GoalEvent;
import com.mennyei.core.match.domain.event.MatchEventType;
import com.mennyei.core.match.domain.event.SubstitutionEvent;
import com.mennyei.core.match.domain.event.lineup.LineUpType;
import com.mennyei.publicweb.club.dto.PlayerMatchStatisticData;
import com.mennyei.publicweb.club.dto.PlayerQuery;
import com.mennyei.publicweb.club.infrastructure.PlayerQueryMongoRepository;
import com.mennyei.publicweb.match.dto.lineup.LineUpQuery;
import com.mennyei.publicweb.match.dto.match.MatchQuery;

@Service
public class PlayerMatchStatisticService {
	
	@Autowired
	private PlayerQueryMongoRepository playerQueryMongoRepository;
	
	public void updatePlayerStatistics(MatchQuery matchQuery) {
		matchQuery.getHomeLineUps().stream().forEach(lineUp -> process(lineUp, matchQuery));
		matchQuery.getAwayLineUps().stream().forEach(lineUp -> process(lineUp, matchQuery));
	} 
	
	public void process(LineUpQuery lineUpQuery, MatchQuery matchQuery) {
		PlayerQuery playerQuery = lineUpQuery.getPlayerQuery();
		
		PlayerMatchStatisticData playerMatchStatisticData = getPlayerMatchStatisticData(matchQuery, playerQuery);
		
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
		
		List<GoalEvent> goals = matchQuery.getMatchResultDetailes().getGoalEventsForPlayer(playerQuery.getId(), MatchEventType.GOAL);
		playerMatchStatisticData.incraseScoredGoalAmount(goals.size());
		
		List<GoalEvent> ownGoals = matchQuery.getMatchResultDetailes().getGoalEventsForPlayer(playerQuery.getId(), MatchEventType.OWN_GOAL);
		playerMatchStatisticData.incraseScoredGoalAmount(ownGoals.size());
		
		List<CardEvent> redCards = matchQuery.getMatchResultDetailes().getCardEventsForPlayer(playerQuery.getId(), MatchEventType.RED_CARD);
		playerMatchStatisticData.incraseScoredGoalAmount(redCards.size());
		
		List<CardEvent> yellowCards = matchQuery.getMatchResultDetailes().getCardEventsForPlayer(playerQuery.getId(), MatchEventType.YELLOW_CARD);
		playerMatchStatisticData.incraseScoredGoalAmount(yellowCards.size());
		
		List<SubstitutionEvent> substitutionIn = matchQuery.getMatchResultDetailes().getSubstitutionInEventForPlayer(playerQuery.getId());
		if(!substitutionIn.isEmpty()) {
			playerMatchStatisticData.incraseSubstitutionIn(1);
		}
		
		List<SubstitutionEvent> substitutionOut = matchQuery.getMatchResultDetailes().getSubstitutionOutEventForPlayer(playerQuery.getId());
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

	private PlayerMatchStatisticData getPlayerMatchStatisticData(MatchQuery matchQuery, PlayerQuery playerQuery) {
		PlayerMatchStatisticData playerMatchStatisticData = playerQuery.getPlayerMatchStatisticDatas().get(matchQuery.getCompetition().getId());
		
		if(playerMatchStatisticData == null) {
			playerMatchStatisticData = PlayerMatchStatisticData.builder().build();
			playerQuery.getPlayerMatchStatisticDatas().put(matchQuery.getCompetition().getId(), playerMatchStatisticData);
		}
		return playerMatchStatisticData;
	}
	
}
