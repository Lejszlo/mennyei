package com.mennyei.core.competition.domain.season;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.mennyei.core.competition.domain.match.domain.Match;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Singular;
import lombok.Value;

@Value
@Builder(builderMethodName="hiddenBuilder")
@AllArgsConstructor
public class Turn implements Comparable<Turn> {
	
	private int index;
	
	@Singular
	private List<Match> matches = new ArrayList<>();
	
	public static TurnBuilder builder(int index) {
		return hiddenBuilder().index(index);
	}

	@Override
	public int compareTo(Turn turn) {
		if(index < turn.getIndex()) {
			return -1;
		}
		if(index > turn.getIndex()) {
			return 1;
		}
		return 0;
	}
	
	public Optional<Match> findMatchByClub(String clubId) {
		return matches.stream().filter(m -> m.containsClub(clubId)).findFirst();
	}
}
