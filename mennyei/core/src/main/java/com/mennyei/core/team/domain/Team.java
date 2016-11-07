package com.mennyei.core.team.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.mennyei.core.player.Player;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
public class Team {
	
	@Getter
	@Setter
	private Long Id;
	
	@Getter
	@Setter
	private String fullName;
	
	@Getter
	@Setter
	private String shortName;
	
	private Set<Player> players = new HashSet<>();
	
	public Set<Player> getPlayers() {
		return Collections.unmodifiableSet(players);
	}
	
	public void addPlayers(Player... players) {
		this.players.addAll(Arrays.asList(players));
	}

	public void removePlayer(Player... player) {
		players.removeAll(Arrays.asList(player));
	}
	
}
