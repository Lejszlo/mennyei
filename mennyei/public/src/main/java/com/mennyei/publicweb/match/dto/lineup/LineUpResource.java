package com.mennyei.publicweb.match.dto.lineup;

import java.util.List;

import com.mennyei.core.match.domain.event.CardEvent;
import com.mennyei.core.match.domain.event.GoalEvent;
import com.mennyei.core.match.domain.event.SubstitutionEvent;
import com.mennyei.core.match.domain.event.lineup.LineUpType;

import lombok.Data;
import lombok.Singular;

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
