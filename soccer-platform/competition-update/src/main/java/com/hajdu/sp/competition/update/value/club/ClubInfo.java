package com.hajdu.sp.competition.update.value.club;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@AllArgsConstructor
@Builder
public class ClubInfo {
	String fullName;

	String shortName;
}
