package com.mennyei.core.match.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.mennyei.core.match.command.AddMatchCommand;
import com.mennyei.core.match.command.MatchCommand;
import com.mennyei.core.match.command.PlayMatchCommand;
import com.mennyei.core.match.command.SetMatchCommand;
import com.mennyei.core.match.domain.event.lineup.LineUp;
import com.mennyei.core.match.event.MatchAdded;
import com.mennyei.core.match.event.MatchPlayed;
import com.mennyei.core.match.event.MatchSet;

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
		return Arrays.asList(MatchPlayed.builder(MatchResultDetailes
				.builder(playMatchCommand.getHomeClubevents(), playMatchCommand.getAwayClubevents()).build().calculateResult())
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