package sp.match.api.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import sp.match.api.value.MatchResultDetails;

@Builder(builderMethodName = "hiddenBuilder")
@Value
@AllArgsConstructor
public class MatchPlayed implements MatchEvents {
	private int fans;
	
	private boolean played;

	private String competitionId;

	private MatchResultDetails matchResultDetailes;

	private String stageId;
	
	public static MatchPlayedBuilder builder(MatchResultDetails matchResultDetailes, String competitionId, String stageId) {
		return hiddenBuilder().matchResultDetailes(matchResultDetailes).competitionId(competitionId).stageId(stageId);
	}
	
}
