package com.mennyei.core.team.events;

import com.mennyei.core.team.domain.Club;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
public class ClubAdded implements ClubEvent {

	private Club club;
	
}
