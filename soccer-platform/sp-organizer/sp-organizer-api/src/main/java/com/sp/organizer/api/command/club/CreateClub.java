package com.sp.organizer.api.command.club;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;
import com.sp.organizer.api.value.club.ClubInfo;

@Value
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Builder
public class CreateClub extends ClubCommand {

	private ClubInfo clubInfo;
	
}
