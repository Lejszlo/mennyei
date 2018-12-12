package com.sp.player.backend.transfer.domain;

import com.sp.player.backend.command.TransferCommand;
import com.sp.player.backend.transfer.command.TransferPlayerCommand;
import com.sp.player.backend.transfer.events.PlayerTransferred;
import io.eventuate.Event;
import io.eventuate.ReflectiveMutableCommandProcessingAggregate;
import value.Transfer;

import java.util.Arrays;
import java.util.List;

/**
 * Created by lejsz on 2016. 11. 22..
 */
public class TransferAggregator extends ReflectiveMutableCommandProcessingAggregate<TransferAggregator, TransferCommand> {

    private Transfer transfer;

    public List<Event> process(TransferPlayerCommand transferPlayerCommand) {
        return Arrays.asList(new PlayerTransferred(transferPlayerCommand.getTransfer()));
    }

    public void apply(PlayerTransferred playerTransferred) {
        transfer = playerTransferred.getTransfer();
    }

}
