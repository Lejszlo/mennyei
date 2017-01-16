package com.mennyei.core.match.domain.event;

import com.mennyei.core.player.domain.Player;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class Substitution extends MatchEvent {
	private Player inner;
	
	private Player outer;
	
}
