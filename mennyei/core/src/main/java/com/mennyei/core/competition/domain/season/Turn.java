package com.mennyei.core.competition.domain.season;

import java.util.ArrayList;
import java.util.List;

import com.mennyei.core.competition.domain.match.domain.Match;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Singular;
import lombok.Value;

@Value
@Builder(builderMethodName="hiddenBuilder")
@AllArgsConstructor
public class Turn {
	
	private int index;
	
	@Singular
	private List<Match> matches = new ArrayList<>();
	
	public static TurnBuilder builder(int index) {
		return hiddenBuilder().index(index);
	}
}
