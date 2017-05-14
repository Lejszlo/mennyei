package com.sp.organizer.backend.match.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.sp.core.backend.value.match.MatchInfo;
import com.sp.core.backend.value.match.MatchResultDetailes;
import com.sp.organizer.backend.match.command.AddMatchCommand;
import com.sp.organizer.backend.match.command.MatchCommand;
import com.sp.organizer.backend.match.command.PlayMatchCommand;
import com.sp.organizer.backend.match.command.SetMatchCommand;
import com.sp.core.backend.value.match.lineup.LineUp;
import com.sp.core.backend.event.match.MatchAdded;
import com.sp.core.backend.event.match.MatchPlayed;
import com.sp.core.backend.event.match.MatchSet;

import io.eventuate.Event;
import io.eventuate.ReflectiveMutableCommandProcessingAggregate;

public class MatchAggregator extends ReflectiveMutableCommandProcessingAggregate<MatchAggregator, MatchCommand> {
	private MatchInfo matchInfo;

	private List<LineUp> homeLineUps = new ArrayList<>();

	private List<LineUp> awayLineUps = new ArrayList<>();
	
	private MatchResultDetailes matchResultDetailes;

	public MatchInfo getMatchInfo() {
		return matchInfo;
	}

	public List<Event> process(AddMatchCommand addMatchCommand) {
		return Arrays.asList(MatchAdded.builder(addMatchCommand.getMatchInfo()).build());
	}

	public List<Event> process(PlayMatchCommand playMatchCommand) {
		MatchResultDetailes matchResultDetailes = MatchResultDetailes.builder(playMatchCommand.getHomeClubevents(), playMatchCommand.getAwayClubevents()).build();
		MatchResultDetailes calculatedResult = matchResultDetailes.calculateResult();
		return Arrays.asList(MatchPlayed.builder(calculatedResult, playMatchCommand.getCompetitionId())
				.played(true)
				.build());
	}
	
	public List<Event> process(SetMatchCommand setMatchCommand) {
		return Arrays.asList(MatchSet.builder()
				.awayLineUps(setMatchCommand.getAwayLineUps())
				.homeLineUps(setMatchCommand.getHomeLineUps())
				.build());
	}

	public void apply(MatchAdded matchAdded) {
		matchInfo = matchAdded.getMatchInfo();
	}

	public void apply(MatchSet matchSet) {
		homeLineUps.addAll(matchSet.getHomeLineUps());
		awayLineUps.addAll(matchSet.getAwayLineUps());
	}

	public void apply(MatchPlayed matchPlayed) {
		matchResultDetailes = matchPlayed.getMatchResultDetailes();
	}
	
}