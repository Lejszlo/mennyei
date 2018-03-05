package com.sp.player.query.player.resource;

import lombok.Data;
import lombok.Singular;
import com.sp.match.api.value.event.CardEvent;
import com.sp.match.api.value.event.GoalEvent;
import com.sp.match.api.value.event.SubstitutionEvent;
import com.sp.match.api.value.lineup.LineUpType;

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
