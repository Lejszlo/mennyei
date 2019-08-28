package com.hajdu.sp.competition.update.event.match;

import com.hajdu.sp.competition.update.value.match.lineup.LineUp;
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
