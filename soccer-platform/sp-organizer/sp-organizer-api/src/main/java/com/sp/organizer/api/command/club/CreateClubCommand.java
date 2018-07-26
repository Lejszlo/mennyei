package com.sp.organizer.api.command.club;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Value;
import com.sp.organizer.api.value.club.ClubInfo;

@Value
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class CreateClubCommand extends ClubCommand {

	private ClubInfo clubInfo;
	
}
