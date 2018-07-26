package com.sp.organizer.query.updater.competition.entity;

import com.sp.core.query.configurations.Interval;
import com.sp.organizer.query.updater.club.entity.ClubDocument;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.DBRef;
import com.sp.organizer.api.value.competition.rule.StageRuleSet;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
public class StageDocument {

	@NotNull
	private String id;

	@NotNull
	private String competitionDocumentId;

	@NonNull
	private String name;

	@NotNull
	private StageRuleSet stageRuleSet;

	@Singular
	private List<TurnDocument> turns;

	@DBRef(lazy = true)
	@Singular
	private Set<ClubDocument> clubs;
	
	@NotNull
	private Interval interval;

}
