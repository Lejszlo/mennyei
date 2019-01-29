package com.hajdu.sp.competition.query.entity;

import com.hajdu.sp.club.lib.value.ClubId;
import com.hajdu.sp.common.Interval;
import com.hajdu.sp.competition.lib.value.StageId;
import com.hajdu.sp.competition.lib.value.rule.StageRuleSet;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.DBRef;

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
