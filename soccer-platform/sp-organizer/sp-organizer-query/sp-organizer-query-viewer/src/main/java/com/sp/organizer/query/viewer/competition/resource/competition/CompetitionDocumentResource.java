package com.sp.organizer.query.viewer.competition.resource.competition;

import com.sp.organizer.api.value.competition.CompetitionInfo;
import org.springframework.hateoas.ResourceSupport;


import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class CompetitionDocumentResource extends ResourceSupport {
	private CompetitionInfo competitionInfo;
}
