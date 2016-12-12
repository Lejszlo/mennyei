package com.mennyei.core.competition.commands;

import java.util.List;

import com.mennyei.core.competition.domain.match.domain.Match;
import com.mennyei.core.competition.domain.season.Turn;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@Builder(builderMethodName="hiddenBuilder")
@EqualsAndHashCode(callSuper=false)
public class AddMatchCommand extends CompetitionCommand {
	private String competitionId;
	private String stageName; 
	private Turn turn; 
	private List<Match> matches;
	
	public static AddMatchCommandBuilder builder(String competitionId, String stageName, Turn turn) {
		return hiddenBuilder().competitionId(competitionId).stageName(stageName).turn(turn);
	}
}
