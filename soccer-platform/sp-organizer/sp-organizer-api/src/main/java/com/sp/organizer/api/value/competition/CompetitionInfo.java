package com.sp.organizer.api.value.competition;

import com.sp.core.query.configurations.Interval;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
public class CompetitionInfo {
	@NonNull
	private String name;

	@NonNull
	private Interval interval;
}
