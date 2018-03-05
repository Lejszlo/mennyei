package com.sp.organizer.api.competition;

import lombok.AllArgsConstructor;
import lombok.Value;
import com.sp.organizer.api.value.club.ClubInfo;

@Value
@AllArgsConstructor
public class AddClubCommand extends ClubCommand {

	private ClubInfo clubInfo;
	
}
