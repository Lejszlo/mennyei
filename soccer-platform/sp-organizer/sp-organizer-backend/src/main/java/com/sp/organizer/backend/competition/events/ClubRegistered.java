package com.sp.organizer.backend.competition.events;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
@AllArgsConstructor
public class ClubRegistered implements CompetitionEvent {

	private Set<String> clubIds;
	
}
