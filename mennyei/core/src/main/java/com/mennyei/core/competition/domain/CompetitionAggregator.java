package com.mennyei.core.competition.domain;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.mennyei.core.club.exception.MaxClubLimitIsReached;
import com.mennyei.core.competition.commands.AddCompetitionCommand;
import com.mennyei.core.competition.commands.AddTurnCommand;
import com.mennyei.core.competition.commands.CompetitionCommand;
import com.mennyei.core.competition.commands.RegisterClubCommand;
import com.mennyei.core.competition.domain.rule.CompetitionRuleSet;
import com.mennyei.core.competition.domain.season.Stage;
import com.mennyei.core.competition.events.ClubRegistered;
import com.mennyei.core.competition.events.CompetitionAdded;
import com.mennyei.core.competition.events.TurnAdded;

import io.eventuate.Event;
import io.eventuate.ReflectiveMutableCommandProcessingAggregate;

public class CompetitionAggregator extends ReflectiveMutableCommandProcessingAggregate<CompetitionAggregator, CompetitionCommand> {

	private CompetitionInfo competitionInfo;

	private CompetitionRuleSet competitionRules;

	private Set<Stage> stages = new HashSet<>();

	private Set<String> clubIds = new HashSet<>();

	public List<Event> process(AddCompetitionCommand addCompetitionCommand) {
		return Arrays.asList(CompetitionAdded.builder().competitionInfo(addCompetitionCommand.getCompetitionInfo())
				.competitionRuleSet(addCompetitionCommand.getCompetitionRules()).stages(addCompetitionCommand.getStages()).build());
	}

	public List<Event> process(RegisterClubCommand registerClubCommand) throws MaxClubLimitIsReached {
		if (competitionRules.getNumberOfTeams() == clubIds.size()) {
			throw new MaxClubLimitIsReached();
		}
		return Arrays.asList(ClubRegistered.builder().clubIds(registerClubCommand.getClubIds()).build());
	}
	
	public List<Event> process(AddTurnCommand addTurnCommand) {
		Optional<Stage> stage = findStageByName(addTurnCommand.getStageName());
		return Arrays.asList(TurnAdded.builder().stageName(addTurnCommand.getStageName()).turn(addTurnCommand.getTurn()).build());
	}


	public void apply(ClubRegistered clubRegistered) {
		clubIds.addAll(clubRegistered.getClubIds());
	}
	
	public void apply(TurnAdded turnAdded) {
		Optional<Stage> stageOptional = findStageByName(turnAdded.getStageName());
		stageOptional.ifPresent(stage -> stage.getTurns().add(turnAdded.getTurn()));
	}

	public void apply(CompetitionAdded competationAdded) {
		competitionInfo = competationAdded.getCompetitionInfo();
		competitionRules = competationAdded.getCompetitionRuleSet();
		stages.addAll(competationAdded.getStages());
	}

	private Optional<Stage> findStageByName(String stageName) {
		return stages.stream().filter(s -> s.getName().equals(stageName)).findFirst();
	}

}
