package com.sp.organizer.api.event.club;

import com.sp.organizer.api.value.club.ClubInfo;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class ClubCreated implements ClubEvent {

	private ClubInfo clubInfo;
	
}
