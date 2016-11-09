package com.mennyei.publicweb.club.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mennyei.core.player.domain.Player;
import com.mennyei.core.team.infrastructure.TeamMemoryDao;
import com.mennyei.publicweb.club.dto.PlayerWithStats;

@Service
public class ClubService {
	
	@Autowired
	private TeamMemoryDao teamMemoryDao;
	
	public Set<PlayerWithStats> getClubPlayers(Long clubId) {
		Set<Player> players = teamMemoryDao.findOne(clubId).getPlayers();
		Set<PlayerWithStats> playerWithStats = new HashSet<>();
		for (Player player : players) {
			int age = Long.valueOf(player.getBirthday().until(LocalDate.now(), ChronoUnit.YEARS)).intValue();
			PlayerWithStats playerWithStat = PlayerWithStats.builder().player(player).age(age).build();
			playerWithStats.add(playerWithStat);
		}
		return playerWithStats;
	}
	
}
