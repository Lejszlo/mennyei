package sp.match.api.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import sp.match.api.value.MatchInfo;

@Builder(builderMethodName="hiddenBuilder")
@Value
@AllArgsConstructor
public class MatchAdded implements MatchEvents {
	private MatchInfo matchInfo;
	
	public static MatchAddedBuilder builder(MatchInfo matchInfo) {
		return hiddenBuilder().matchInfo(matchInfo);
	}
}
