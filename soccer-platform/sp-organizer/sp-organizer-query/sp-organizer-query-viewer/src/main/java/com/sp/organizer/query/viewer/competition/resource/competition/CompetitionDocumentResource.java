package com.sp.organizer.query.viewer.competition.resource.competition;

import com.sp.organizer.api.value.competition.CompetitionInfo;
import com.sp.organizer.query.viewer.competition.resource.stage.StageDocumentResource;
import lombok.Singular;
import org.springframework.hateoas.ResourceSupport;


import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper=false)
public class CompetitionDocumentResource extends ResourceSupport {
	private CompetitionInfo competitionInfo;
	@Singular
	private List<StageDocumentResource> stages;
}
