package com.sp.organizer.api.event.organizer;

import io.eventuate.Event;
import io.eventuate.EventEntity;

@EventEntity(entity="com.sp.organizer.command.aggregator.organizer.domain.OrganizerAggregate")
public interface OrganizerEvent extends Event {
}
