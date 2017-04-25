package com.mennyei.core.transfer.service;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mennyei.core.club.commands.AddPlayerToClub;
import com.mennyei.core.club.infrastructure.ClubAggregateRepository;
import com.mennyei.core.player.service.infrastructure.TransferAggregatorRepository;
import com.mennyei.core.transfer.command.TransferPlayerCommand;
import com.mennyei.core.transfer.domain.Transfer;

/**
 * Created by lejsz on 2016. 11. 22..
 */
@Service
public class TransferService {

    @Autowired
    private TransferAggregatorRepository transferAggregatorRepository;

    @Autowired
    private ClubAggregateRepository clubRepository;
    
    public void transferPlayer(Transfer transfer) throws ExecutionException, InterruptedException {
        TransferPlayerCommand transferPlayerCommand = TransferPlayerCommand.builder().transfer(transfer).build();
        transferAggregatorRepository.save(transferPlayerCommand).get();
        clubRepository.update(transfer.getTargetTeamId(), AddPlayerToClub.builder(transfer.getTargetTeamId(), transfer.getPlayerId()).build()).get();
        if(transfer.getSourceTeamId() != null) {
        	clubRepository.update(transfer.getSourceTeamId(), AddPlayerToClub.builder(transfer.getTargetTeamId(), transfer.getPlayerId()).build()).get();
    	}
    }

}
