package com.mennyei.publicweb.club.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mennyei.core.competition.service.MatchPlayerStatisticService;

@Service
public class PlayerMatchStatisticService {
	
	@Autowired
	private MatchPlayerStatisticService matchPlayerStatisticService; 
	
//	public PlayerMatchStatisticData collectPlayerMatchStatisticDatas(Match match, Player player) {
//		return PlayerMatchStatisticData.builder()
//										.yellowCardAmount(matchPlayerStatisticService.getCardEventsByPlayer(match, player, CardEventType.YELLOW).size())
//										.redCardAmount(matchPlayerStatisticService.getCardEventsByPlayer(match, player, CardEventType.RED).size())
//										.substitutionIn(matchPlayerStatisticService.playerWasSubstutuionIn(match, player))
//										.substitutionOut(matchPlayerStatisticService.playerWasSubstutuionOut(match, player))
//										.build();
//	}
	
}
