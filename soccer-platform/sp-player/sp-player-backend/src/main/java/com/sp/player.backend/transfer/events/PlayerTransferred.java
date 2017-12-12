package com.sp.player.backend.transfer.events;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.Value;
import value.Transfer;

/**
 * Created by lejsz on 2016. 11. 22..
 */
@Value
@AllArgsConstructor
public class PlayerTransferred implements TransferEvent {

    @NonNull
    private Transfer transfer;

}
