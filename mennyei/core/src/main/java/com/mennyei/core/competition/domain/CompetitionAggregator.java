package com.mennyei.core.competition.domain;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.mennyei.core.club.exception.MaxClubLimitIsReached;
import com.mennyei.core.competition.commands.AddCompetitionCommand;
import com.mennyei.core.competition.commands.AddMatchCommand;
import com.mennyei.core.competition.commands.CompetitionCommand;
import com.mennyei.core.competition.commands.PlayMatchCommand;
import com.mennyei.core.competition.commands.RegisterClubCommand;
import com.mennyei.core.competition.domain.match.domain.Match;
import com.mennyei.core.competition.domain.rule.CompetitionRules;
import com.mennyei.core.competition.domain.season.Stage;
import com.mennyei.core.competition.domain.season.Turn;
import com.mennyei.core.competition.events.ClubRegistered;
import com.mennyei.core.competition.events.CompetitionAdded;
import com.mennyei.core.competition.events.MatchAdded;
import com.mennyei.core.competition.events.MatchPlayed;

import io.eventuate.Event;
import io.eventuate.ReflectiveMutableCommandProcessingAggregate;

public class CompetitionAggregator
		extends ReflectiveMutableCommandProcessingAggregate<CompetitionAggregator, CompetitionCommand> {

	private CompetitionInfo competitionInfo;

	private CompetitionRules competitionRules;

	private Set<Stage> stages = new HashSet<>();

	private Set<String> clubIds = new HashSet<>();

	public List<Event> process(AddCompetitionCommand addCompetitionCommand) {
		return Arrays.asList(CompetitionAdded.builder().competitionInfo(addCompetitionCommand.getCompetition())
				.competitionRules(addCompetitionCommand.getCompetitionRules()).stages(addCompetitionCommand.getStages()).build());
	}

	public List<Event> process(AddMatchCommand addMatchCommand) {
		return Arrays.asList(MatchAdded.builder(addMatchCommand.getCompetitionId(), addMatchCommand.getStageName(),
				addMatchCommand.getTurnIndex()).matches(addMatchCommand.getMatches()).build());
	}

	public List<Event> process(PlayMatchCommand fillMatchCommand) {
		return Arrays.asList(MatchPlayed
				.builder(fillMatchCommand.getCompetitionId(), fillMatchCommand.getStageName(),
						fillMatchCommand.getTurnIndex(), fillMatchCommand.getHomeClubId())
				.events(fillMatchCommand.getEvents()).build());
	}

	public List<Event> process(RegisterClubCommand registerClubCommand) throws MaxClubLimitIsReached {
		if (competitionRules.getNumberOfTeams() == clubIds.size()) {
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
		stages.addAll(competationAdded.getStages());
	}

	public void apply(MatchAdded matchAdded) {
		Optional<Stage> stage = findStageByName(matchAdded.getStageName());
		Optional<Turn> optionalTurn = findTurnByIndex(matchAdded.getTurnIndex(), stage.get());
		Turn turn = null;
		if(!optionalTurn.isPresent()) {
			turn = Turn.builder(matchAdded.getTurnIndex()).build();
			stage.get().getTurns().add(turn);
		}
		turn.getMatches().addAll(matchAdded.getMatches());
	}

	public void apply(MatchPlayed matchFilledWithEvents) {
		Optional<Stage> stage = findStageByName(matchFilledWithEvents.getStageName());
		Optional<Turn> turn = findTurnByIndex(matchFilledWithEvents.getTurnIndex(), stage.get());
		Optional<Match> match = findMatchByTurn(turn.get(), matchFilledWithEvents.getHomeClubId());
		match.get().getEvents().addAll(matchFilledWithEvents.getEvents());
		match.get().setPlayed(true);
	}

	private Optional<Stage> findStageByName(String stageName) {
		return stages.stream().filter(s -> s.getName().equals(stageName)).findFirst();
	}

	private Optional<Turn> findTurnByIndex(int index, Stage stage) {
		return stage.getTurns().stream().filter(t -> t.getIndex() == index).findFirst();
	}

	private Optional<Match> findMatchByTurn(Turn turn, String homeClubId) {
		return turn.getMatches().stream().filter(m -> m.getHomeClubId().equals(homeClubId)).findFirst();
	}
}
