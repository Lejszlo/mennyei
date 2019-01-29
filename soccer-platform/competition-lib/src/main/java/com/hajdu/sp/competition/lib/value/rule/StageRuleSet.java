package com.hajdu.sp.competition.lib.value.rule;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
@AllArgsConstructor
public class StageRuleSet {

	int numberOfTeams;
	
	int pointsForWin = 3;
	
	int pointsForDraw = 1;
	
	int pointsForLose = 0;
	
	List<SortingRule> sortingRules;
	
	boolean hasOverTime = false;

	boolean hasPenalties = false;
	
}
