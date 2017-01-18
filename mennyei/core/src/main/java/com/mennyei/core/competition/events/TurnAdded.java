package com.mennyei.core.competition.events;

import com.mennyei.core.competition.domain.season.Turn;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
@AllArgsConstructor
public class TurnAdded implements CompetitionEvent {
	private String stageName;
	
	private Turn turn;
}
