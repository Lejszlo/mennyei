package sp.competition.api.command;

import lombok.*;
import sp.competition.api.value.CompetitionInfo;

@Value
@Builder
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
public class CreateCompetition extends CompetitionCommand {
	
	@NonNull
	private CompetitionInfo competitionInfo;
}
