package com.mennyei.core.transfer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mennyei.core.team.service.TeamPlayerService;
import com.mennyei.core.transfer.aggregator.Transfer;
import com.mennyei.core.transfer.infrastructure.TransferMemoryDao;

@Service
public class PlayerTransferService {

	@Autowired
	private TeamPlayerService teamPlayerService;
	
	@Autowired
	private TransferMemoryDao transferRepository;
	
	public void transferPlayer(Transfer transfer) {
		teamPlayerService.removePlayers(transfer.getSourceTeam(), transfer.getPlayer());
		
		teamPlayerService.addPlayers(transfer.getTargetTeam(), transfer.getPlayer());
		
		transferRepository.save(transfer);
	}
	
}
