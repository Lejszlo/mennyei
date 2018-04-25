package com.sp.organizer.api.value.competition.season;

import java.util.*;

import com.sp.core.query.configurations.Interval;
import com.sp.organizer.api.value.competition.rule.StageRuleSet;
import lombok.*;

@Value
@Builder
@AllArgsConstructor
public class Stage {

	private UUID id = UUID.randomUUID();

	@NonNull
	private String name;

	@NonNull
	private StageRuleSet stageRuleSet;

	@Singular
	private List<Turn> turns;

	@Singular
	private Set<String> clubIds;

	@NonNull
	private Interval interval;

}
