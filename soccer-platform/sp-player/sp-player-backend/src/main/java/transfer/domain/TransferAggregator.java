package transfer.domain;

import java.util.Arrays;
import java.util.List;

import com.sp.core.backend.value.player.Transfer;
import transfer.command.TransferCommand;
import transfer.command.TransferPlayerCommand;
import transfer.events.PlayerTransferred;

import io.eventuate.Event;
import io.eventuate.ReflectiveMutableCommandProcessingAggregate;

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
