package com.sp.core.backend.event.match;

import com.sp.core.backend.value.match.MatchInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Builder(builderMethodName="hiddenBuilder")
@Value
@AllArgsConstructor
public class MatchAdded implements MatchEvents {
	private MatchInfo matchInfo;
	
	public static MatchAddedBuilder builder(MatchInfo matchInfo) {
		return hiddenBuilder().matchInfo(matchInfo);
	}
}
