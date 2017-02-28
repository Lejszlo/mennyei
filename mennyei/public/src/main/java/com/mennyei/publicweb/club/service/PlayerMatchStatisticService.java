package com.mennyei.publicweb.club.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mennyei.core.match.domain.event.CardEvent;
import com.mennyei.core.match.domain.event.GoalEvent;
import com.mennyei.core.match.domain.event.MatchEventType;
import com.mennyei.core.match.domain.event.lineup.LineUpType;
import com.mennyei.publicweb.club.dto.PlayerMatchStatisticData;
import com.mennyei.publicweb.club.dto.PlayerQuery;
import com.mennyei.publicweb.club.infrastructure.PlayerQueryMongoRepository;
import com.mennyei.publicweb.match.dto.LineUpQuery;
import com.mennyei.publicweb.match.dto.MatchQuery;

@Service
public class PlayerMatchStatisticService {
	
	@Autowired
	private PlayerQueryMongoRepository playerQueryMongoRepository;
	
	public void updatePlayerStatistics(MatchQuery matchQuery) {
		matchQuery.getHomeLineUps().stream().forEach(lineUp -> process(lineUp, matchQuery));
	} 
	
	public void process(LineUpQuery lineUpQuery, MatchQuery matchQuery) {
		PlayerQuery playerQuery = lineUpQuery.getPlayerQuery();
		
		PlayerMatchStatisticData playerMatchStatisticData = playerQuery.getPlayerMatchStatisticDatas().get(matchQuery.getCompetition().getId());
		
		if(playerMatchStatisticData == null) {
			playerMatchStatisticData = PlayerMatchStatisticData.builder().build();
			playerQuery.getPlayerMatchStatisticDatas().put(matchQuery.getCompetition().getId(), playerMatchStatisticData);
		}
		
		playerMatchStatisticData.setTotalMatch(playerMatchStatisticData.getTotalMatch() + 1);
		if(LineUpType.STARTER.equals(lineUpQuery.getLineUpType())) {
			playerMatchStatisticData.setStarter(playerMatchStatisticData.getStarter() + 1);
		}
		if(LineUpType.SUBSTITUTION.equals(lineUpQuery.getLineUpType())) {
			playerMatchStatisticData.setSubtitution(playerMatchStatisticData.getSubtitution() + 1);
		}
		
		List<GoalEvent> goals = matchQuery.getMatchResultDetailes().getGoalEventsForPlayer(playerQuery.getId());
		playerMatchStatisticData.setScoredGoalAmount(playerMatchStatisticData.getScoredGoalAmount() + goals.size());
		
		List<CardEvent> redCards = matchQuery.getMatchResultDetailes().getCardEventsForPlayer(playerQuery.getId(), MatchEventType.RED_CARD);
		playerMatchStatisticData.setScoredGoalAmount(playerMatchStatisticData.getScoredGoalAmount() + redCards.size());
		
		List<CardEvent> yellowCards = matchQuery.getMatchResultDetailes().getCardEventsForPlayer(playerQuery.getId(), MatchEventType.YELLOW_CARD);
		playerMatchStatisticData.setScoredGoalAmount(playerMatchStatisticData.getScoredGoalAmount() + yellowCards.size());
		
		playerQueryMongoRepository.save(playerQuery);
	}
	
}
