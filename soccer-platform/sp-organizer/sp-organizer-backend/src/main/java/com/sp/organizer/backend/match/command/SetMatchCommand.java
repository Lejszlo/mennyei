package com.sp.organizer.backend.match.command;

import java.util.List;

import com.sp.core.backend.value.match.lineup.LineUp;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Singular;
import lombok.Value;

@Builder(builderMethodName = "hiddenBuilder")
@Value
@EqualsAndHashCode(callSuper = false)
public class SetMatchCommand extends MatchCommand {
	
	@Singular
	private List<LineUp> homeLineUps;
	
	@Singular
	private List<LineUp> awayLineUps;

	public static SetMatchCommandBuilder builder() {
		return hiddenBuilder();
	}
}
