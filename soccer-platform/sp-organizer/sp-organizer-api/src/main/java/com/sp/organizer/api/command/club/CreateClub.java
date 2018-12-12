package com.sp.organizer.api.command.club;

import com.sp.organizer.api.value.club.ClubInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Builder
public class CreateClub extends ClubCommand {

	private ClubInfo clubInfo;
	
}
