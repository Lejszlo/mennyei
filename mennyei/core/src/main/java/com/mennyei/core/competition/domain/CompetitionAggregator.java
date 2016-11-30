package com.mennyei.core.competition.domain;

import com.mennyei.core.competition.commands.AddCompetitionCommand;
import com.mennyei.core.competition.commands.CompetitionCommand;
import com.mennyei.core.competition.commands.RegisterClubCommand;
import com.mennyei.core.competition.events.ClubRegistered;
import com.mennyei.core.competition.events.CompetitionAdded;
import io.eventuate.Event;
import io.eventuate.ReflectiveMutableCommandProcessingAggregate;

import java.util.*;

public class CompetitionAggregator extends ReflectiveMutableCommandProcessingAggregate<CompetitionAggregator, CompetitionCommand> {

	private CompetitionInfo competition;

	private Set<String> clubIds = new HashSet<>();
	
	public List<Event> process(AddCompetitionCommand addCompetitionCommand) {
		if(addCompetitionCommand.getCompetition().equals(competition)) {
			return Collections.emptyList();
		}
		return Arrays.asList(CompetitionAdded.builder().competition(addCompetitionCommand.getCompetition()).build());
	}
	
	public List<Event> process(RegisterClubCommand registerClubCommand) {
		return Arrays.asList(ClubRegistered.builder().clubIds(registerClubCommand.getClubIds()).build());
	}
	
	public void apply(ClubRegistered clubRegistered) {
		clubIds.addAll(clubRegistered.getClubIds());
	}
	
	public void apply(CompetitionAdded competationAdded) {
		competition = competationAdded.getCompetition();
	}

}
