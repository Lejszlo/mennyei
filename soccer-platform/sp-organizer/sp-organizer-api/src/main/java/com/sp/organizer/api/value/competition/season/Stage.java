package com.sp.organizer.api.value.competition.season;

import com.sp.core.query.configurations.Interval;
import com.sp.organizer.api.value.competition.StageId;
import com.sp.organizer.api.value.competition.rule.StageRuleSet;
import lombok.*;

import java.util.List;
import java.util.Set;

@Value
@Builder
@AllArgsConstructor
public class Stage {

	@NonNull
	private StageId id;

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
