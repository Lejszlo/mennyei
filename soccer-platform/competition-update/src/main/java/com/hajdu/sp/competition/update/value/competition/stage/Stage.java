package com.hajdu.sp.competition.update.value.competition.stage;

import com.hajdu.sp.competition.update.util.Interval;
import com.hajdu.sp.competition.update.value.club.ClubId;
import com.hajdu.sp.competition.update.value.competition.turn.Turn;
import com.hajdu.sp.competition.update.value.competition.ids.StageId;
import com.hajdu.sp.competition.update.value.competition.rule.StageRuleSet;
import lombok.*;

import java.util.List;
import java.util.Set;

@Value
@Builder
@AllArgsConstructor
public class Stage implements Comparable<Stage> {

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

	@Override
	public int compareTo(Stage that) {
		return this.interval.compareTo(that.getInterval());
	}
}
