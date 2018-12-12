package sp.competition.api.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import sp.competition.api.value.CompetitionInfo;

@Builder
@Value
@AllArgsConstructor
public class CompetitionCreated implements CompetitionEvent {

	@NonNull
	private CompetitionInfo competitionInfo;
}
