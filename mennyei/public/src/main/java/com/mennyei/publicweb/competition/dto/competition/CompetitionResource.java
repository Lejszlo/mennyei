package com.mennyei.publicweb.competition.dto.competition;

import org.springframework.hateoas.ResourceSupport;

import com.mennyei.core.competition.domain.CompetitionInfo;
import com.mennyei.core.competition.domain.rule.CompetitionRuleSet;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class CompetitionResource extends ResourceSupport {

	private CompetitionInfo competitionInfo;
	
	private CompetitionRuleSet competitionRuleSet;
	
}
