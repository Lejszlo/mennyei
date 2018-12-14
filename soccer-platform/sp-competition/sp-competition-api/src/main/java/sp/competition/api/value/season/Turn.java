package sp.competition.api.value.season;

import com.sp.match.api.value.MatchId;
import lombok.*;
import sp.common.Interval;
import sp.competition.api.value.TurnId;

import java.util.List;

import static java.util.Comparator.comparing;

@Value
@Builder(builderMethodName="hiddenBuilder")
@AllArgsConstructor
public class Turn implements Comparable<Turn> {

	@NonNull
	TurnId turnId;

	@Singular
	List<MatchId> matches;

	Interval interval;

	@Override
	public int compareTo(Turn that) {
	    return comparing(Turn::getTurnId).compare(this, that);
	}
}
