package com.hajdu.sp.match.lib.event;

import com.hajdu.sp.match.lib.value.lineup.LineUp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Singular;
import lombok.Value;

import java.util.List;

@Builder
@Value
@AllArgsConstructor
public class MatchSet implements MatchEvent {

	@Singular
	private List<LineUp> homeLineUps;
	
	@Singular
	private List<LineUp> awayLineUps;
	
}
