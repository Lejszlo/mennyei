package com.hajdu.sp.competition.update.event.match;

import com.hajdu.sp.competition.update.value.match.MatchInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Builder
@Value
@AllArgsConstructor
public class MatchCreated implements MatchEvent {

	@NonNull
	MatchInfo matchInfo;
	
}
