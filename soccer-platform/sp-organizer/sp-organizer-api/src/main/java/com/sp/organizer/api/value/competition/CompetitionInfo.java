package com.sp.organizer.api.value.competition;

import com.sp.core.query.configurations.Interval;
import com.sp.organizer.api.value.organizer.OrganizerInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import javax.validation.constraints.NotNull;

@Value
@Builder
@AllArgsConstructor
public class CompetitionInfo {
	@NonNull
	private String name;

	@NonNull
	private Interval interval;

	@NotNull
	private OrganizerInfo organizerInfo;

	@NotNull
	private String description;
}
