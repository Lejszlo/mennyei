package com.sp.player.backend.transfer.service;

import java.util.concurrent.ExecutionException;

import com.sp.player.backend.transfer.command.TransferPlayerCommand;
import com.sp.player.backend.transfer.infrastructure.TransferAggregatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import value.Transfer;


@Service
public class TransferService {

    @Autowired
    private TransferAggregatorRepository transferAggregatorRepository;

    public void transferPlayer(Transfer transfer) throws ExecutionException, InterruptedException {
        TransferPlayerCommand transferPlayerCommand = new TransferPlayerCommand(transfer);
        transferAggregatorRepository.save(transferPlayerCommand).get();
//        clubRepository.update(transfer.getTargetTeamId(), new AddPlayerToClub(transfer.getTargetTeamId(), transfer.getPlayer())).get();
//        if(transfer.getSourceTeamId() != null) {
//        	clubRepository.update(transfer.getSourceTeamId(), AddPlayerToClub.builder(transfer.getTargetTeamId(), transfer.getPlayerId()).build()).get();
//    	}
    }

}
