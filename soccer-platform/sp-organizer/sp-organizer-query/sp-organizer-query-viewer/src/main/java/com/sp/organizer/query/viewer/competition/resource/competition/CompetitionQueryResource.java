package com.sp.organizer.query.viewer.competition.resource.competition;

import com.sp.core.query.configurations.Interval;
import value.competition.CompetitionInfo;
import value.competition.rule.StageRuleSet;
import org.springframework.hateoas.ResourceSupport;


import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class CompetitionQueryResource extends ResourceSupport {

	private CompetitionInfo competitionInfo;

	private Interval interval;
	
	private StageRuleSet stageRuleSet;

}
