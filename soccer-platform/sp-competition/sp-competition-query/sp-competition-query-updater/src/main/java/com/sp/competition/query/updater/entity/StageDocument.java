package com.sp.competition.query.updater.entity;

import com.sp.core.query.configurations.Interval;
import com.sp.organizer.query.updater.club.entity.ClubDocument;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.DBRef;
import sp.competition.api.value.rule.StageRuleSet;

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

	@NotNull
	private String seasonDocumentId;

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
