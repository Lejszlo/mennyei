package com.mennyei.core.competition.domain.match.domain.match.event.card;

import com.mennyei.core.competition.domain.match.domain.match.event.MatchEvent;
import com.mennyei.core.player.domain.Player;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class CardEvent extends MatchEvent {
	private Player sufferer;
	
	private CardEventType type;
}
