package com.mennyei.core.competition.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.mennyei.core.team.domain.Team;

import lombok.Data;

@Data
public class Competition {
	
	private List<Turn> turns = new ArrayList<>();
	
	private String name;
	
	private Set<Team> teams;

	public Competition save(Long Id) {
		return null;
	}
	
	public Competition findById(Long Id) {
		return null;
	}
	
	public List<Turn> getPlayedTurns() {
		turns.stream().filter(turn -> turn.isPlayed());
		return turns;
	}
	
	public List<Turn> getFeatureTurns() {
		turns.stream().filter(turn -> !turn.isPlayed());
		return turns;
	}
	
	public Optional<Turn> getCurrentTurn() {
		return getFeatureTurns().stream().findFirst();
	}
	
}
