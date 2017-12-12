package com.sp.player.backend.transfer.events;

import io.eventuate.Event;
import io.eventuate.EventEntity;

/**
 * Created by lejsz on 2016. 11. 22..
 */
@EventEntity(entity="TransferAggregator")
public interface TransferEvent extends Event {
}
