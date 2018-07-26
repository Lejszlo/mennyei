package com.sp.organizer.api.event.competition;

import com.sp.organizer.api.value.competition.CompetitionInfo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Builder
@Value
@AllArgsConstructor
public class CompetitionCreated implements CompetitionEvent {

	@NonNull
	private CompetitionInfo competitionInfo;
}
