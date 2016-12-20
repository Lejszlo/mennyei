package com.mennyei.core.competition.events;

import java.util.List;

import com.mennyei.core.competition.domain.CompetitionInfo;
import com.mennyei.core.competition.domain.rule.CompetitionRuleSet;
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
	
	private CompetitionRuleSet competitionRuleSet;
	
	@NonNull
	@Singular
	private List<Stage> stages;
}
