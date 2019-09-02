package com.hajdu.sp.competition.update.value.competition.turn;

import com.hajdu.sp.competition.update.util.Interval;
import com.hajdu.sp.competition.update.value.match.MatchId;
import com.hajdu.sp.competition.update.value.competition.ids.TurnId;
import lombok.*;

import java.util.List;

import static java.util.Comparator.comparing;

@Value
@Builder
@AllArgsConstructor
public class Turn implements Comparable<Turn> {

	@NonNull
	TurnId turnId;

	@Singular
	List<MatchId> matches;

	@NonNull
	Interval interval;

	@Override
	public int compareTo(Turn that) {
	    return comparing(Turn::getTurnId).compare(this, that);
	}
}
