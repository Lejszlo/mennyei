package com.sp.match.command.aggregator.domain;

import com.sp.match.api.command.AddMatchCommand;
import com.sp.match.api.command.MatchCommand;
import com.sp.match.api.command.PlayMatchCommand;
import com.sp.match.api.command.SetMatchCommand;
import com.sp.match.api.event.MatchAdded;
import com.sp.match.api.event.MatchPlayed;
import com.sp.match.api.event.MatchSet;
import io.eventuate.Event;
import io.eventuate.ReflectiveMutableCommandProcessingAggregate;
import com.sp.match.api.value.MatchInfo;
import com.sp.match.api.value.MatchResultDetails;
import com.sp.match.api.value.lineup.LineUp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MatchAggregator extends ReflectiveMutableCommandProcessingAggregate<MatchAggregator, MatchCommand> {
	private MatchInfo matchInfo;

	private List<LineUp> homeLineUps = new ArrayList<>();

	private List<LineUp> awayLineUps = new ArrayList<>();
	
	private MatchResultDetails matchResultDetailes;

	public MatchInfo getMatchInfo() {
		return matchInfo;
	}

	public List<Event> process(AddMatchCommand addMatchCommand) {
		return Collections.singletonList(MatchAdded.builder(addMatchCommand.getMatchInfo()).build());
	}

	public List<Event> process(PlayMatchCommand playMatchCommand) {
		MatchResultDetails matchResultDetailes = MatchResultDetails.builder(playMatchCommand.getHomeClubevents(), playMatchCommand.getAwayClubevents()).build();
		MatchResultDetails calculatedResult = matchResultDetailes.calculateResult();
		return Collections.singletonList(MatchPlayed.builder(playMatchCommand.getHomeClubId(), playMatchCommand.getAwayClubId(), calculatedResult, playMatchCommand.getCompetitionId(), playMatchCommand.getStageId())
				.played(true)
				.build());
	}
	
	public List<Event> process(SetMatchCommand setMatchCommand) {
		return Collections.singletonList(MatchSet.builder()
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