package com.mennyei.core.transfer.events;

import io.eventuate.Event;
import io.eventuate.EventEntity;

/**
 * Created by lejsz on 2016. 11. 22..
 */
@EventEntity(entity="com.mennyei.core.transfer.domain.TransferAggregator")
public interface TransferEvent extends Event {
}
