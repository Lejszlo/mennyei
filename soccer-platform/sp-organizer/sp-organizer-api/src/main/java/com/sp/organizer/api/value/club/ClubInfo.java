package com.sp.organizer.api.value.club;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class ClubInfo {
	private String fullName;

	private String name;
}
