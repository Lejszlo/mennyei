package com.mennyei.core.team.domain;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.mennyei.core.player.domain.Player;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Team {
	
	@Getter
	@Setter
	@Id
	private Long id;
	
	@Getter
	@Setter
	private String fullName;
	
	@Getter
	@Setter
	private String shortName;
	
	@OneToMany
	private Set<Player> players;
	
	public Set<Player> getPlayers() {
		if(players == null) {
			players = new HashSet<>();
		}
		return players;
	}
	
	public void addPlayers(Player... players) {
		getPlayers().addAll(Arrays.asList(players));
	}

	public void removePlayer(Player... player) {
		getPlayers().removeAll(Arrays.asList(player));
	}
	
}
