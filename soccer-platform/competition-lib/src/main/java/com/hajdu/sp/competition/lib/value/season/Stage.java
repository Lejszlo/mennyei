package com.hajdu.sp.competition.lib.value.season;

import com.hajdu.sp.club.lib.value.ClubId;
import com.hajdu.sp.common.Interval;
import lombok.*;
import com.hajdu.sp.competition.lib.value.StageId;
import com.hajdu.sp.competition.lib.value.rule.StageRuleSet;

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
	private Set<ClubId> clubIds;

	@NonNull
	private Interval interval;

}
