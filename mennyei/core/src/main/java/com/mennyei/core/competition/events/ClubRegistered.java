package com.mennyei.core.competition.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.util.Set;

@Builder
@Value
@AllArgsConstructor
public class ClubRegistered implements CompetitionEvent {

	private Set<String> clubIds;
	
}
