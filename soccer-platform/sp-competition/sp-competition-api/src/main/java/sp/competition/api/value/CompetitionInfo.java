package sp.competition.api.value;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import sp.common.Interval;

import javax.validation.constraints.NotNull;

@Value
@Builder
@AllArgsConstructor
public class CompetitionInfo {
	@NonNull
	private String name;

	@NonNull
	private Interval interval;

	@NotNull
	private String description;
}
