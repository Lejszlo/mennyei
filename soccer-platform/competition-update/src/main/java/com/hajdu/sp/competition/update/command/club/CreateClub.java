package com.hajdu.sp.competition.update.command.club;

import com.hajdu.sp.competition.update.value.club.ClubInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
public class CreateClub extends ClubCommand {

	ClubInfo clubInfo;
	
}
