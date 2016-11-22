package com.mennyei.core.transfer.domain;

import com.mennyei.core.transfer.command.TransferCommand;
import com.mennyei.core.transfer.command.TransferPlayerCommand;
import com.mennyei.core.transfer.events.PlayerTransferred;
import io.eventuate.Event;
import io.eventuate.ReflectiveMutableCommandProcessingAggregate;

import java.util.Arrays;
import java.util.List;

/**
 * Created by lejsz on 2016. 11. 22..
 */
public class TransferAggregator extends ReflectiveMutableCommandProcessingAggregate<TransferAggregator, TransferCommand> {

    private Transfer transfer;

    public List<Event> process(TransferPlayerCommand transferPlayerCommand) {
        return Arrays.asList(PlayerTransferred.builder().transfer(transferPlayerCommand.getTransfer()).build());
    }

    public void apply(PlayerTransferred playerTransferred) {
        transfer = playerTransferred.getTransfer();
    }

}
