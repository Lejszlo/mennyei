package com.mennyei.core.match.command;

import com.mennyei.core.match.domain.MatchInfo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(builderMethodName="hiddenBuilder")
@AllArgsConstructor
public class AddMatchCommand extends MatchCommand {
	
	private MatchInfo matchInfo;
	
	public static AddMatchCommandBuilder builder(MatchInfo matchInfo) {
		return hiddenBuilder().matchInfo(matchInfo);
	}
}
