package com.mennyei.core.team.domain;

import com.mennyei.core.team.commands.ClubCommand;

import io.eventuate.ReflectiveMutableCommandProcessingAggregate;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Club extends ReflectiveMutableCommandProcessingAggregate<Club, ClubCommand> {
	
	private String fullName;
	
	private String shortName;
	
//	private Set<Player> players;
//	
//	public Set<Player> getPlayers() {
//		if(players == null) {
//			players = new HashSet<>();
//		}
//		return players;
//	}
//	
//	public void addPlayers(Player... players) {
//		getPlayers().addAll(Arrays.asList(players));
//	}
//
//	public void removePlayer(Player... player) {
//		getPlayers().removeAll(Arrays.asList(player));
//	}
	
}
