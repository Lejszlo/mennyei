package com.mennyei.core.match.command;

import java.util.ArrayList;
import java.util.List;

import com.mennyei.core.match.domain.event.lineup.LineUp;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Singular;
import lombok.Value;

@Builder(builderMethodName = "hiddenBuilder")
@Value
@EqualsAndHashCode(callSuper = false)
public class SetMatchCommand extends MatchCommand {
	
	@Singular
	private List<LineUp> homeLineUps = new ArrayList<>();
	
	@Singular
	private List<LineUp> awayLineUps = new ArrayList<>();

	public static SetMatchCommandBuilder builder() {
		return hiddenBuilder();
	}
}
