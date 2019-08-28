package com.hajdu.sp.competition.update.value.competition;

import com.hajdu.sp.competition.update.util.Interval;
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

	@NonNull
	private String description;
}
