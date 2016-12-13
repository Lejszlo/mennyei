package com.mennyei.core.competition.domain.match.domain.match.event.substitution;

import com.mennyei.core.competition.domain.match.domain.match.event.MatchEvent;
import com.mennyei.core.player.domain.Player;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class Substitution extends MatchEvent {
	private Player inner;
	
	private Player outer;
	
}
