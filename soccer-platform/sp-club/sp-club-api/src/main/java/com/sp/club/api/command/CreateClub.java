package com.sp.club.api.command;

import com.sp.club.api.value.ClubInfo;
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
