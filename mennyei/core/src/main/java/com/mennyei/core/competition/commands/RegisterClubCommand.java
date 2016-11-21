package com.mennyei.core.competition.commands;

import lombok.Builder;
import lombok.Value;

import java.util.Set;

@Builder
@Value
public class RegisterClubCommand extends CompetitionCommand {

	private Set<String> clubIds;
	
}
