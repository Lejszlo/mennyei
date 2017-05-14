package com.mennyei.util.player.resource;

import com.sp.core.backend.value.match.event.CardEvent;
import com.sp.core.backend.value.match.event.GoalEvent;
import com.sp.core.backend.value.match.event.SubstitutionEvent;
import lombok.Data;
import lombok.Singular;
import com.sp.core.backend.value.match.lineup.LineUpType;

import java.util.List;

@Data
public class LineUpResource {
	
	private String playerId;
	
	private String playerName;
	
	private int shirtNumber;
	
	private LineUpType lineUpType;
	
	@Singular
	private List<GoalEvent> goalEvents;
	
	@Singular
	private List<CardEvent> cardEvents;
	
	@Singular
	private List<SubstitutionEvent> substitutionEvents;
}
