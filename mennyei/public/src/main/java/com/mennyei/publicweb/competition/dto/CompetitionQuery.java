package com.mennyei.publicweb.competition.dto;

import java.util.List;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mennyei.core.competition.domain.CompetitionInfo;
import com.mennyei.core.competition.domain.rule.CompetitionRuleSet;
import com.mennyei.publicweb.club.dto.ClubQuery;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;

@Document
@Builder
@Data
public class CompetitionQuery {

	@Id
	private String id;
	
	@DBRef
	@Singular
	private Set<ClubQuery> clubs;
	
	@Singular
	private List<StageQuery> stages;
	
	private CompetitionRuleSet competitionRuleSet;
	
	private CompetitionInfo competitionInfo;
}
