package com.mennyei.core.competition.commands;

import com.mennyei.core.competition.domain.Competition;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AddCompetitionCommand extends CompetitionCommand {
	
	private Competition competition;
}
