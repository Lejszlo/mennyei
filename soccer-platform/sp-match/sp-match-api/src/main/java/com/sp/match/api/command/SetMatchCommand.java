package com.sp.match.api.command;

import com.sp.match.api.value.lineup.LineUp;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Singular;
import lombok.Value;

import java.util.List;

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
