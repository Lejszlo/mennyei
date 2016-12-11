package com.mennyei.core.competition.commands;

import com.mennyei.core.competition.domain.CompetitionInfo;
import com.mennyei.core.competition.domain.rule.CompetitionRules;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AddCompetitionCommand extends CompetitionCommand {
	
	private CompetitionInfo competition;
	
	private CompetitionRules competitionRules;
}
