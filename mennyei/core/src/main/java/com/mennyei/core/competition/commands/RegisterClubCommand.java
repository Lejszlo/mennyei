package com.mennyei.core.competition.commands;

import java.util.Set;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class RegisterClubCommand extends CompetitionCommand {

	private Set<String> clubIds;
	
}
