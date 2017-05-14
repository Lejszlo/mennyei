package transfer.events;

import com.sp.core.backend.value.player.Transfer;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.Value;

/**
 * Created by lejsz on 2016. 11. 22..
 */
@Value
@AllArgsConstructor
public class PlayerTransferred implements TransferEvent {

    @NonNull
    private Transfer transfer;

}
