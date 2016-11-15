package com.mennyei.core.competition.domain;

import java.util.Arrays;
import java.util.List;

import com.mennyei.core.competition.commands.CompetitionCommand;
import com.mennyei.core.competition.commands.RegisterCompetitionCommand;
import com.mennyei.core.competition.events.CompetationRegistered;

import io.eventuate.Event;
import io.eventuate.ReflectiveMutableCommandProcessingAggregate;

public class CompetitionAggregator extends ReflectiveMutableCommandProcessingAggregate<CompetitionAggregator, CompetitionCommand> {

	public List<Event> process(RegisterCompetitionCommand registerCompetitionCommand) {
		return Arrays.asList(CompetationRegistered.builder().competition(registerCompetitionCommand.getCompetition()).build());
	}
	
}
