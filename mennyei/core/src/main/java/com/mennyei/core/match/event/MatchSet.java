package com.mennyei.core.match.event;

import java.util.List;

import com.mennyei.core.match.domain.event.lineup.LineUp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Singular;
import lombok.Value;

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
