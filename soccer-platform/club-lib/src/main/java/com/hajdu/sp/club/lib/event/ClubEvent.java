package com.hajdu.sp.club.lib.event;

import io.eventuate.Event;
import io.eventuate.EventEntity;

@EventEntity(entity="com.hajdu.sp.club.update.aggregator.domain.ClubAggregate")
public interface ClubEvent extends Event {

}
