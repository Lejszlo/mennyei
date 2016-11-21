package com.mennyei.core.team.commands;

import com.mennyei.core.team.domain.Club;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper=false)
@Builder
public class AddClubCommand extends ClubCommand {

	private Club club;
	
}
