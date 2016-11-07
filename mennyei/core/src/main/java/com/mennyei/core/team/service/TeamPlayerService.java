package com.mennyei.core.team.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mennyei.core.player.Player;
import com.mennyei.core.team.domain.Team;

import lombok.NonNull;

@Service
public class TeamPlayerService {

	@Autowired
	private TeamRepository teamRepository;
	
	public void addPlayers(@NonNull Team team, Player... player) {
		if(team.getPlayers().contains(player)) {
			return;
		}
		
		team.addPlayers(player);
	}
	
	public void removePlayers(@NonNull Team team, Player... player) {
		team.removePlayer(player);
	}
	
	public Optional<Player> findPlayer(Team team, Long id) {
		team = teamRepository.load(team);
		
		for (Player player : team.getPlayers()) {
			if(player.getId().equals(id)) {
				return Optional.of(player);
			}
		}
		
		return Optional.empty();
	}
	
	public Team createEmptyTeam() {
		return Team.builder().Id(0L).fullName("Unregisterd").shortName("UR").build();
	}
	
}
