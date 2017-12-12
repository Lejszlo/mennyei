package sp.match.api.command;

import java.util.List;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Singular;
import lombok.Value;
import sp.match.api.value.event.MatchEvent;

import javax.validation.constraints.NotNull;

@Builder(builderMethodName = "hiddenBuilder")
@Value
@EqualsAndHashCode(callSuper = false)
public class PlayMatchCommand extends MatchCommand {
	
	@Singular
	private List<MatchEvent> homeClubevents;
	
	@Singular
	private List<MatchEvent> awayClubevents;

	@NotNull
	private String competitionId;

	public static PlayMatchCommandBuilder builder(String competitionId) {
		return hiddenBuilder().competitionId(competitionId);
	}
}
