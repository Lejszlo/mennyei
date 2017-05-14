package com.sp.organizer.backend.competition.domain;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import com.sp.organizer.backend.competition.domain.season.Stage;
import com.sp.organizer.backend.competition.domain.rule.CompetitionRuleSet;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Competition {

	private CompetitionInfo competitionInfo;

	private CompetitionRuleSet competitionRules;

	private Set<Stage> stages = new HashSet<>();

	private Set<String> clubIds = new HashSet<>();

	private Optional<Stage> findStageByName(String stageName) {
		return stages.stream().filter(s -> s.getName().equals(stageName)).findFirst();
	}

}
