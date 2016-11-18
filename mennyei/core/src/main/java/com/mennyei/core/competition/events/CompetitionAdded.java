package com.mennyei.core.competition.events;

import com.mennyei.core.competition.domain.Competition;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
@AllArgsConstructor
public class CompetitionAdded implements CompetitionEvent {
	
	private Competition competition;
}
