package com.mennyei.core.competition.commands;

import com.mennyei.core.competition.domain.season.Turn;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class AddTurnCommand extends CompetitionCommand {
	private String stageName;
	
	private Turn turn;
	
}
