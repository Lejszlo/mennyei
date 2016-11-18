package com.mennyei.core.team.events;

import com.mennyei.core.team.domain.Club;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ClubAdded implements ClubEvent {

	private Club club;
	
}
