package com.sp.club.api.value;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@AllArgsConstructor
@Builder
public class ClubInfo {
	String fullName;

	String name;
}
