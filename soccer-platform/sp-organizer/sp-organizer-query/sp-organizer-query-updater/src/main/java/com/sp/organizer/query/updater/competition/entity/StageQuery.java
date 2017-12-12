package com.sp.organizer.query.updater.competition.entity;

import com.sp.core.query.configurations.Interval;
import com.sp.organizer.query.updater.club.entity.ClubQuery;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.DBRef;
import value.competition.rule.StageRuleSet;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Data
@Builder(builderMethodName="hiddenBuilder")
@AllArgsConstructor
public class StageQuery {

	@NotNull
	private int index;

	@NotNull
	private String competitionQuery;

	@NonNull
	private String name;

	@NotNull
	private StageRuleSet stageRuleSet;

	@Singular
	private List<TurnQuery> turns;

	@DBRef(lazy = true)
	@Singular
	private Set<ClubQuery> clubs;
	
	@DBRef
	private TableQuery tableQuery;

	@NotNull
	private Interval interval;

	public static StageQueryBuilder builder(String name, StageRuleSet stageRuleSet, int index) {
		return hiddenBuilder()
				.name(name)
				.stageRuleSet(stageRuleSet)
				.index(index);
	}

}
