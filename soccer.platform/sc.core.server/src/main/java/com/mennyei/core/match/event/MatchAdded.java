package com.mennyei.core.match.event;

import com.mennyei.core.match.domain.MatchInfo;

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
