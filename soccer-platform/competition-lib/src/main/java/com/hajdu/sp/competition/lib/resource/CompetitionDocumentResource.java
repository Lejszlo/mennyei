package com.hajdu.sp.competition.lib.resource;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Singular;
import org.springframework.hateoas.ResourceSupport;
import com.hajdu.sp.competition.lib.value.CompetitionInfo;

import java.util.List;


@Data
@EqualsAndHashCode(callSuper=false)
public class CompetitionDocumentResource extends ResourceSupport {
	private CompetitionInfo competitionInfo;

	@Singular
	private List<SeasonDocumentResource> seasons;
}
