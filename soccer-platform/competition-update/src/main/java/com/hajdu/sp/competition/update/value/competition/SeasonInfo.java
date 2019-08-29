package com.hajdu.sp.competition.update.value.competition;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
public class SeasonInfo {
	@NonNull
	private String name;

	@NonNull
	private String description;
}
