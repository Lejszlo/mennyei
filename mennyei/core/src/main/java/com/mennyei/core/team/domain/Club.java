package com.mennyei.core.team.domain;

import javax.persistence.Id;

import com.mennyei.core.team.commands.ClubCommand;

import io.eventuate.ReflectiveMutableCommandProcessingAggregate;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
public class Club extends ReflectiveMutableCommandProcessingAggregate<Club, ClubCommand> {
	
	@Setter
	@Id
	private Long id;
	
	@Getter
	@Setter
	private String fullName;
	
	@Getter
	@Setter
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
