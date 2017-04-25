package com.mennyei.core.transfer.events;

import com.mennyei.core.transfer.domain.Transfer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

/**
 * Created by lejsz on 2016. 11. 22..
 */
@Builder
@Value
@AllArgsConstructor
public class PlayerTransferred implements TransferEvent {

    private Transfer transfer;

}
