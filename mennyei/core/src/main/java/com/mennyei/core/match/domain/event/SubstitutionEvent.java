package com.mennyei.core.match.domain.event;

import com.mennyei.core.player.domain.Player;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
public class SubstitutionEvent extends MatchEvent {
	private Player inner;
	
	private Player outer;
	
}
