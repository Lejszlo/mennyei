package com.mennyei.core.competition.domain;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.mennyei.core.competition.commands.AddCompetitionCommand;
import com.mennyei.core.competition.commands.CompetitionCommand;
import com.mennyei.core.competition.commands.RegisterClubCommand;
import com.mennyei.core.competition.events.ClubRegistered;
import com.mennyei.core.competition.events.CompetitionAdded;

import io.eventuate.Event;
import io.eventuate.ReflectiveMutableCommandProcessingAggregate;

public class CompetitionAggregator extends ReflectiveMutableCommandProcessingAggregate<CompetitionAggregator, CompetitionCommand> {

	private Set<String> clubIds = new HashSet<>();
	
	public List<Event> process(AddCompetitionCommand addCompetitionCommand) {
		return Arrays.asList(CompetitionAdded.builder().competition(addCompetitionCommand.getCompetition()).build());
	}
	
	public List<Event> process(RegisterClubCommand registerClubCommand) {
		return Arrays.asList(ClubRegistered.builder().clubIds(registerClubCommand.getClubIds()).build());
	}
	
	public void apply(ClubRegistered clubRegistered) {
		clubIds.addAll(clubRegistered.getClubIds());
	}
	
	public void apply(CompetitionAdded competationAdded) {}
	
	
	
}
