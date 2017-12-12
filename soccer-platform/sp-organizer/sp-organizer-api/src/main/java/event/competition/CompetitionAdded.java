package event.competition;

import value.competition.CompetitionInfo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
@AllArgsConstructor
public class CompetitionAdded implements CompetitionEvent {

	private CompetitionInfo competitionInfo;
}
