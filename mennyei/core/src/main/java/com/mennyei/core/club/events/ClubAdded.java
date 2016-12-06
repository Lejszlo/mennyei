package com.mennyei.core.club.events;

import com.mennyei.core.club.domain.value.ClubInfo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
public class ClubAdded implements ClubEvent {

	private ClubInfo clubInfo;
	
}
