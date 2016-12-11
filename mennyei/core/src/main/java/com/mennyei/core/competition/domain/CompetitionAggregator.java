package com.mennyei.core.competition.domain;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.mennyei.core.club.exception.MaxClubLimitIsReached;
import com.mennyei.core.competition.commands.AddCompetitionCommand;
import com.mennyei.core.competition.commands.CompetitionCommand;
import com.mennyei.core.competition.commands.RegisterClubCommand;
import com.mennyei.core.competition.domain.rule.CompetitionRules;
import com.mennyei.core.competition.events.ClubRegistered;
import com.mennyei.core.competition.events.CompetitionAdded;

import io.eventuate.Event;
import io.eventuate.ReflectiveMutableCommandProcessingAggregate;

public class CompetitionAggregator extends ReflectiveMutableCommandProcessingAggregate<CompetitionAggregator, CompetitionCommand> {

	private CompetitionInfo competitionInfo;
	
	private CompetitionRules competitionRules;

	private Set<String> clubIds = new HashSet<>();
	
	public List<Event> process(AddCompetitionCommand addCompetitionCommand) {
		return Arrays.asList(CompetitionAdded.builder().competitionInfo(addCompetitionCommand.getCompetition()).competitionRules(addCompetitionCommand.getCompetitionRules()).build());
	}
	
	public List<Event> process(RegisterClubCommand registerClubCommand) throws MaxClubLimitIsReached {
		if(competitionRules.getNumberOfTeams() == clubIds.size()) {
			throw new MaxClubLimitIsReached();
		}
		return Arrays.asList(ClubRegistered.builder().clubIds(registerClubCommand.getClubIds()).build());
	}
	
	public void apply(ClubRegistered clubRegistered) {
		clubIds.addAll(clubRegistered.getClubIds());
	}
	
	public void apply(CompetitionAdded competationAdded) {
		competitionInfo = competationAdded.getCompetitionInfo();
		competitionRules = competationAdded.getCompetitionRules();
	}

}
