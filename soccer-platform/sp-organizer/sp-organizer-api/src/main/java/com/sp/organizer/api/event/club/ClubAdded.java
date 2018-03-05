package com.sp.organizer.api.event.club;

import lombok.AllArgsConstructor;
import lombok.Value;
import com.sp.organizer.api.value.club.ClubInfo;

@Value
@AllArgsConstructor
public class ClubAdded implements ClubEvent {

	private ClubInfo clubInfo;
	
}
