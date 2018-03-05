package com.sp.organizer.api.event.club;

import io.eventuate.Event;
import io.eventuate.EventEntity;

@EventEntity(entity="com.sp.organizer.command.aggregator.club.domain.ClubAggregate")
public interface ClubEvent extends Event {

}
