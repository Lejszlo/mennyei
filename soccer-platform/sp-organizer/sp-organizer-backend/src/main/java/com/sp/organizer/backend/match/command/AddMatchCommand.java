package com.sp.organizer.backend.match.command;

import com.sp.core.backend.value.match.MatchInfo;

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
