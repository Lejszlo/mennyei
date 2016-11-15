package com.mennyei.core.team.events;

import com.mennyei.core.team.domain.Club;

import io.eventuate.Event;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ClubAdded implements Event {

	private Club club;
	
}
