package sp.match.api.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Singular;
import lombok.Value;
import sp.match.api.value.lineup.LineUp;

import java.util.List;

@Builder(builderMethodName="hiddenBuilder")
@Value
@AllArgsConstructor
public class MatchSet implements MatchEvents {

	@Singular
	private List<LineUp> homeLineUps;
	
	@Singular
	private List<LineUp> awayLineUps;
	
	public static MatchSetBuilder builder() {
		return hiddenBuilder();
	}
	
}
