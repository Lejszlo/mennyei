package sp.competition.api.resource;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Singular;
import org.springframework.hateoas.ResourceSupport;
import sp.competition.api.value.CompetitionInfo;

import java.util.List;


@Data
@EqualsAndHashCode(callSuper=false)
public class CompetitionDocumentResource extends ResourceSupport {
	private CompetitionInfo competitionInfo;

	@Singular
	private List<SeasonDocumentResource> seasons;
}
