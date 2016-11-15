package com.mennyei.core.competition.events;

import com.mennyei.core.competition.domain.Competition;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CompetationRegistered implements CompetitionEvent {

	private Competition competition;
	
}
