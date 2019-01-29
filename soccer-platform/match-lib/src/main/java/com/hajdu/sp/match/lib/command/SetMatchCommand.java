package com.hajdu.sp.match.lib.command;

import com.hajdu.sp.match.lib.value.lineup.LineUp;
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
