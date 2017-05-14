package com.sp.organizer.backend.competition.dto.competition;

import com.sp.organizer.backend.club.domain.ClubQuery;
import com.sp.organizer.backend.competition.domain.CompetitionInfo;
import com.sp.organizer.backend.competition.domain.rule.CompetitionRuleSet;
import com.sp.organizer.backend.competition.dto.stage.StageQuery;
import lombok.Builder;
import lombok.Data;
import lombok.Singular;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Set;

@Document
@Builder
@Data
public class CompetitionQuery {

	@Id
	private String id;
	
	@DBRef(lazy = true)
	@Singular
	private Set<ClubQuery> clubs;
	
	@Singular
	private List<StageQuery> stages;
	
	private CompetitionRuleSet competitionRuleSet;
	
	private CompetitionInfo competitionInfo;
}
