package com.mennyei.core;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mennyei.core.player.domain.Player;
import com.mennyei.core.player.inrfastructure.PlayerMemoryDao;
import com.mennyei.core.team.domain.Team;
import com.mennyei.core.team.infrastructure.TeamMemoryDao;
import com.mennyei.core.transfer.aggregator.Transfer;
import com.mennyei.core.transfer.service.PlayerTransferService;

@Service
public class FillDatabase {
	
	@Autowired
	private PlayerTransferService playerTransferService;
	
	@Autowired
	private TeamMemoryDao teamMemoryDao;
	
	@Autowired
	private PlayerMemoryDao playerMemoryDao;

	public void fillTeam() {
		Team team = Team.builder().id(2L).fullName("Vamosoroszi").shortName("VKSE").build();
		teamMemoryDao.save(team);
		Team emptyTeam = Team.builder().id(1L).fullName("Unregisterd").shortName("UR").build();
		teamMemoryDao.save(emptyTeam);
		for (int i = 0; i < 16; i++) {
			Player player = Player.builder().id(1L).birthday(LocalDate.of(1990,1,9)).name("Kiss Pista").number(10).build();
			playerMemoryDao.save(player);
			Transfer transfer = Transfer.builder().player(player).sourceTeam(emptyTeam).targetTeam(team).build();
			playerTransferService.transferPlayer(transfer);
		}
	}
	
}
