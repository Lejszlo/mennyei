package com.sp.organizer.api.resource;

import com.sp.organizer.api.value.competition.CompetitionInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Singular;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper=false)
public class CompetitionDocumentResource extends ResourceSupport {
	private CompetitionInfo competitionInfo;

	@Singular
	private List<SeasonDocumentResource> seasons;
}
