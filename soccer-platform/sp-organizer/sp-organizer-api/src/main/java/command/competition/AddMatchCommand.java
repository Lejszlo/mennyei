package command.competition;

import lombok.*;
import value.competition.season.Turn;

@Value
@Builder(builderMethodName="hiddenBuilder")
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class AddMatchCommand extends CompetitionCommand {
	@NonNull
	private String competitionId;
	
	@NonNull
	private String stageName; 
	
	private Turn turn;
	
	public static AddMatchCommandBuilder builder(String competitionId, String stageName, Turn turn) {
		return hiddenBuilder().competitionId(competitionId).stageName(stageName).turn(turn);
	}
}
