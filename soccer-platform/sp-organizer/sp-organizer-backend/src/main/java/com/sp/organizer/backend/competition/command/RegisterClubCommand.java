package com.sp.organizer.backend.competition.command;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.util.Set;

@Value
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class RegisterClubCommand extends CompetitionCommand {

	private Set<String> clubIds;
	
}
