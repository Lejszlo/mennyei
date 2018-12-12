package sp.competition.api.command;

import lombok.*;
import sp.competition.api.value.season.Turn;

@Value
@Builder
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class AddMatch extends CompetitionCommand {
	@NonNull
	String competitionId;
	
	@NonNull
	String stageName;

	@NonNull
	Turn turn;
}
