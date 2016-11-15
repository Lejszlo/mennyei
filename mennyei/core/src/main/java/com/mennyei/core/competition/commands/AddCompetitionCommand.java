package com.mennyei.core.competition.commands;

import com.mennyei.core.competition.domain.Competition;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper=false)
@Builder
public class AddCompetitionCommand extends CompetitionCommand {
	
	private Competition competition;
}
