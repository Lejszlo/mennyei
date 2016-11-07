package com.mennyei.core.team.infrastructure;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;

import com.mennyei.core.player.Player;
import com.mennyei.core.team.domain.Team;
import com.mennyei.core.team.service.TeamPlayerService;
import com.mennyei.core.team.service.TeamRepository;
import com.mennyei.core.transfer.aggregator.Transfer;
import com.mennyei.core.transfer.service.PlayerTransferService;

public class TeamMemoryDao implements TeamRepository {

	@Autowired
	private TeamPlayerService teamPlayerService;
	
	@Autowired
	private PlayerTransferService playerTransferService;
	
	@Override
	public Team findById(Long id) {
		Team team = Team.builder().fullName("Vamosoroszi").shortName("VKSE").build();
		
		for (int i = 0; i < 16; i++) {
			Player player = Player.builder().birthday(LocalDate.now()).name("Kiss Pista").build();
			Transfer transfer = Transfer.builder().player(player).sourceTeam(teamPlayerService.createEmptyTeam()).targetTeam(team).build();
			playerTransferService.transferPlayer(transfer);
		}
		
		return team;
	}

	@Override
	public Team save(Team team) {
		return null;
	}

	@Override
	public Team load(Team team) {
		return null;
	}


}
