package value.competition.season;

import java.util.List;

import com.sp.core.query.configurations.Interval;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Singular;
import lombok.Value;


import static java.util.Comparator.*;

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
