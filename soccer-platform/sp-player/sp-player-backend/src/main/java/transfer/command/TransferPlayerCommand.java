package transfer.command;

import com.sp.core.backend.value.player.Transfer;

import lombok.AllArgsConstructor;
import lombok.Value;

/**
 * Created by lejsz on 2016. 11. 22..
 */
@Value
@AllArgsConstructor
public class TransferPlayerCommand extends TransferCommand {

    private Transfer transfer;

}
