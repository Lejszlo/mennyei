package com.mennyei.core.competition.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Competition {
	
//	private List<Turn> turns = new ArrayList<>();
	
	private String name;
	
//	private Set<Club> teams;

//	public List<Turn> getPlayedTurns() {
//		turns.stream().filter(turn -> turn.isPlayed());
//		return turns;
//	}
//	
//	public List<Turn> getFeatureTurns() {
//		turns.stream().filter(turn -> !turn.isPlayed());
//		return turns;
//	}
//	
//	public Optional<Turn> getCurrentTurn() {
//		return getFeatureTurns().stream().findFirst();
//	}
	
}
