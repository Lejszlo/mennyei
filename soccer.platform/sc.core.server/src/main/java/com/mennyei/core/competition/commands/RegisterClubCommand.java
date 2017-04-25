package com.mennyei.core.competition.commands;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.Set;

@Value
@AllArgsConstructor
public class RegisterClubCommand extends CompetitionCommand {

	private Set<String> clubIds;
	
}
