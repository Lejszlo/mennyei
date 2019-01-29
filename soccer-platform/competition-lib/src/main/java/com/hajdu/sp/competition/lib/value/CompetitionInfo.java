package com.hajdu.sp.competition.lib.value;

import com.hajdu.sp.common.Interval;
import lombok.*;

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
