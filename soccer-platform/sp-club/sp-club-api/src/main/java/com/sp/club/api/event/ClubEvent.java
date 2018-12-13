package com.sp.club.api.event;

import io.eventuate.Event;
import io.eventuate.EventEntity;

@EventEntity(entity="sp.club.command.aggregator.domain.ClubAggregate")
public interface ClubEvent extends Event {

}
