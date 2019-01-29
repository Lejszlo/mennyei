package com.hajdu.sp.competition.lib.event;

import lombok.*;
import com.hajdu.sp.competition.lib.value.CompetitionInfo;

@Builder
@Value
@AllArgsConstructor
public class CompetitionCreated implements CompetitionEvent {

	@NonNull
	private CompetitionInfo competitionInfo;
}
