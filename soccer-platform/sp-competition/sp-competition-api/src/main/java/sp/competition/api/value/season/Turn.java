package sp.competition.api.value.season;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Singular;
import lombok.Value;
import sp.common.Interval;

import java.util.List;

import static java.util.Comparator.comparing;

@Value
@Builder(builderMethodName="hiddenBuilder")
@AllArgsConstructor
public class Turn implements Comparable<Turn> {
	
	private int index;
	
	@Singular
	private List<String> matches;

	private Interval interval;

	public static TurnBuilder builder(int index) {
		return hiddenBuilder().index(index);
	}

	@Override
	public int compareTo(Turn that) {
	    return comparing(Turn::getIndex).compare(this, that);
	}
}
