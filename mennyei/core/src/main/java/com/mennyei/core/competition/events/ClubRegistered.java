package com.mennyei.core.competition.events;

import java.util.Set;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class ClubRegistered implements CompetitionEvent {

	private Set<String> clubIds;
	
}
