package com.sp.organizer.backend.club.commands;

import com.sp.core.backend.value.club.ClubInfo;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class AddClubCommand extends ClubCommand {

	private ClubInfo clubInfo;
	
}
