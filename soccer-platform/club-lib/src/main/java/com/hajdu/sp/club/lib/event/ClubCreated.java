package com.hajdu.sp.club.lib.event;

import com.hajdu.sp.club.lib.value.ClubInfo;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class ClubCreated implements ClubEvent {

	private ClubInfo clubInfo;
	
}
