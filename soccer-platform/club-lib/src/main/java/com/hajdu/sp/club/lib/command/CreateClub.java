package com.hajdu.sp.club.lib.command;

import com.hajdu.sp.club.lib.value.ClubInfo;
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
