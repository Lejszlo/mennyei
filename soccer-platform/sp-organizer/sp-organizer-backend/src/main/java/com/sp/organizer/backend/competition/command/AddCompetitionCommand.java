package com.sp.organizer.backend.competition.command;

import java.util.Set;

import com.sp.organizer.backend.competition.domain.CompetitionInfo;
import com.sp.organizer.backend.competition.domain.rule.CompetitionRuleSet;
import com.sp.organizer.backend.competition.domain.season.Stage;

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
	private CompetitionInfo competitionInfo;
	
	@NonNull
	private CompetitionRuleSet competitionRules;
	
	@NonNull
	@Singular
	private Set<Stage> stages;
}
