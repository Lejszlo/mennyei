package com.sp.organizer.api.value.competition;

import com.sp.core.query.configurations.Interval;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class CompetitionInfo {
	private String name;

	private Interval interval;
}
