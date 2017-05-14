package com.sp.organizer.backend.competition.dto.competition;

import com.sp.organizer.backend.competition.domain.CompetitionInfo;
import com.sp.organizer.backend.competition.domain.rule.CompetitionRuleSet;
import org.springframework.hateoas.ResourceSupport;


import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class CompetitionResource extends ResourceSupport {

	private CompetitionInfo competitionInfo;
	
	private CompetitionRuleSet competitionRuleSet;
	
}
