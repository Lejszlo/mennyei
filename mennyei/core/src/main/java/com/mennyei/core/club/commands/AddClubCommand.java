package com.mennyei.core.club.commands;

import com.mennyei.core.club.domain.ClubInfo;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AddClubCommand extends ClubCommand {

	private ClubInfo clubInfo;
	
}
