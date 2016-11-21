package com.mennyei.publicweb.club.service;

import com.mennyei.core.match.domain.Match;
import com.mennyei.core.match.domain.match.event.card.CardEventType;
import com.mennyei.core.match.service.MatchPlayerStatisticService;
import com.mennyei.core.player.domain.Player;
import com.mennyei.publicweb.club.dto.PlayerMatchStatisticData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerMatchStatisticService {
	
	@Autowired
	private MatchPlayerStatisticService matchPlayerStatisticService; 
	
	public PlayerMatchStatisticData collectPlayerMatchStatisticDatas(Match match, Player player) {
		return PlayerMatchStatisticData.builder()
										.yellowCardAmount(matchPlayerStatisticService.getCardEventsByPlayer(match, player, CardEventType.YELLOW).size())
										.redCardAmount(matchPlayerStatisticService.getCardEventsByPlayer(match, player, CardEventType.RED).size())
										.substitutionIn(matchPlayerStatisticService.playerWasSubstutuionIn(match, player))
										.substitutionOut(matchPlayerStatisticService.playerWasSubstutuionOut(match, player))
										.build();
	}
	
}
