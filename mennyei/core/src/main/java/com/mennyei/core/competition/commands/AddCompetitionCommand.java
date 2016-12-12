package com.mennyei.core.competition.commands;

import java.util.Set;

import com.mennyei.core.competition.domain.CompetitionInfo;
import com.mennyei.core.competition.domain.rule.CompetitionRules;
import com.mennyei.core.competition.domain.season.Stage;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.Singular;
import lombok.Value;

@Value
@Builder
@EqualsAndHashCode(callSuper=false)
public class AddCompetitionCommand extends CompetitionCommand {
	
	@NonNull
	private CompetitionInfo competition;
	
	@NonNull
	private CompetitionRules competitionRules;
	
	@NonNull
	@Singular
	private Set<Stage> stages;
}
