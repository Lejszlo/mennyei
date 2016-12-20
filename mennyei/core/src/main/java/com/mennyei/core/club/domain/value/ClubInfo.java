package com.mennyei.core.club.domain.value;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
@AllArgsConstructor
public class ClubInfo {
	private String fullName;

	private String name;
}
