package com.mennyei.core.competition.events;

import java.util.Set;

import com.mennyei.core.competition.domain.CompetitionInfo;
import com.mennyei.core.competition.domain.rule.CompetitionRules;
import com.mennyei.core.competition.domain.season.Stage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NonNull;
import lombok.Singular;
import lombok.Value;

@Builder
@Value
@AllArgsConstructor
public class CompetitionAdded implements CompetitionEvent {
	
	private CompetitionInfo competitionInfo;
	
	private CompetitionRules competitionRules;
	
	@NonNull
	@Singular
	private Set<Stage> stages;
}
