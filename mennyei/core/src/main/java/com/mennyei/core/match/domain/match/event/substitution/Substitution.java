package com.mennyei.core.match.domain.match.event.substitution;

import com.mennyei.core.match.domain.match.event.MatchEvent;
import com.mennyei.core.player.Player;

public class Substitution extends MatchEvent {
	private Player inner;
	
	private Player outer;
}
