package com.hajdu.sp.competition.lib.value.season;

import com.hajdu.sp.common.Interval;
import com.hajdu.sp.match.lib.value.MatchId;
import lombok.*;
import com.hajdu.sp.competition.lib.value.TurnId;

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
