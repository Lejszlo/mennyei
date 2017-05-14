package com.sp.core.backend.event.organizer.club;

import com.sp.core.backend.value.club.ClubInfo;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class ClubAdded implements ClubEvent {

	private ClubInfo clubInfo;
	
}
