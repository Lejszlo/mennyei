package com.hajdu.sp.competition.update.event.club;

import com.hajdu.sp.competition.update.value.club.ClubInfo;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class ClubCreated implements ClubEvent {

	private ClubInfo clubInfo;
	
}
