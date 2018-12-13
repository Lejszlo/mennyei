package com.sp.competition.query.updater.entity;

import com.sp.club.api.value.ClubId;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.DBRef;
import sp.common.Interval;
import sp.competition.api.value.StageId;
import sp.competition.api.value.rule.StageRuleSet;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
public class StageDocument {

    @NotNull
	private StageId id;

	@NonNull
	private String name;

	@NotNull
	private StageRuleSet stageRuleSet;

	@Singular
	private List<TurnDocument> turns;

	@DBRef(lazy = true)
	@Singular
	private Set<ClubId> clubs;
	
	@NotNull
	private Interval interval;

}
