package com.sp.core.backend.event.organizer.club;

import io.eventuate.Event;
import io.eventuate.EventEntity;

@EventEntity(entity="ClubAggregate")
public interface ClubEvent extends Event {

}
