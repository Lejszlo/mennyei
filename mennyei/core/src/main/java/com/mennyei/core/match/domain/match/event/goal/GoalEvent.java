package com.mennyei.core.match.domain.match.event.goal;

import com.mennyei.core.match.domain.match.event.MatchEvent;
import com.mennyei.core.player.domain.Player;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class GoalEvent extends MatchEvent {
	
	private Player scorer;

	private GoalEventType type;
}
