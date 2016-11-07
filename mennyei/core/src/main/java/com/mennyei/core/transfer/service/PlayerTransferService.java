package com.mennyei.core.transfer.service;

import org.springframework.stereotype.Service;

import com.mennyei.core.team.service.TeamPlayerService;
import com.mennyei.core.transfer.aggregator.Transfer;

@Service
public class PlayerTransferService {

	private TeamPlayerService teamPlayerService;
	
	private TransferRepository transferRepository;
	
	public void transferPlayer(Transfer transfer) {
		teamPlayerService.removePlayers(transfer.getSourceTeam(), transfer.getPlayer());
		
		teamPlayerService.addPlayers(transfer.getTargetTeam(), transfer.getPlayer());
		
		transferRepository.save(transfer);
	}
	
}
