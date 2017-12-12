package command.competition;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.Value;
import value.competition.CompetitionInfo;
import value.competition.season.Stage;

@Value
@Builder
@EqualsAndHashCode(callSuper=false)
public class SaveCompetitionCommand extends CompetitionCommand {
	
	@NonNull
	private CompetitionInfo competitionInfo;
}
