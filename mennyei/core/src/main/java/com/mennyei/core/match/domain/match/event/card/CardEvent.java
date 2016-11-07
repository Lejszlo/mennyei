package com.mennyei.core.match.domain.match.event.card;

import com.mennyei.core.match.domain.match.event.MatchEvent;
import com.mennyei.core.player.Player;

public class CardEvent extends MatchEvent {
	private Player sufferer;
	
	private CardEventType type;
}
