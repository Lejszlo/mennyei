package com.mennyei.core.team.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mennyei.core.player.domain.Player;
import com.mennyei.core.team.domain.Team;
import com.mennyei.core.team.infrastructure.TeamMemoryDao;

import lombok.NonNull;

@Service
public class TeamPlayerService {

	@Autowired
	private TeamMemoryDao teamRepository;
	
	public void addPlayers(@NonNull Team team, Player... player) {
		if(team.getPlayers().contains(player)) {
			return;
		}
		
		team.addPlayers(player);
		teamRepository.saveAndFlush(team);
	}
	
	public void removePlayers(@NonNull Team team, Player... player) {
		team.removePlayer(player);
		teamRepository.saveAndFlush(team);
	}
	
	public Optional<Player> findPlayer(Team team, Long id) {
		team = teamRepository.findOne(team.getId());
		
		for (Player player : team.getPlayers()) {
			if(player.getId().equals(id)) {
				return Optional.of(player);
			}
		}
		
		return Optional.empty();
	}
	
}
