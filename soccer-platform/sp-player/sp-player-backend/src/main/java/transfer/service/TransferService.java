package transfer.service;

import java.util.concurrent.ExecutionException;

import com.sp.core.backend.value.player.Transfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import transfer.command.TransferPlayerCommand;
import transfer.infrastructure.TransferAggregatorRepository;


/**
 * Created by lejsz on 2016. 11. 22..
 */
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
