package com.mennyei.core.club.commands;

import com.mennyei.core.club.domain.value.ClubInfo;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class AddClubCommand extends ClubCommand {

	private ClubInfo clubInfo;
	
}
