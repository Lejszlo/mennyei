package com.sp.match.api.event;

import com.sp.match.api.value.lineup.LineUp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Singular;
import lombok.Value;

import java.util.List;

@Builder(builderMethodName="hiddenBuilder")
@Value
@AllArgsConstructor
public class MatchSet implements MatchEvent {

	@Singular
	private List<LineUp> homeLineUps;
	
	@Singular
	private List<LineUp> awayLineUps;
	
	public static MatchSetBuilder builder() {
		return hiddenBuilder();
	}
	
}
