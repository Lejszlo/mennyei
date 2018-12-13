package com.sp.club.api.event;

import com.sp.club.api.value.ClubInfo;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class ClubCreated implements ClubEvent {

	private ClubInfo clubInfo;
	
}
