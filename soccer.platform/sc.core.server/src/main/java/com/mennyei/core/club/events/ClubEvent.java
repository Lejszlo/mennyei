package com.mennyei.core.club.events;

import io.eventuate.Event;
import io.eventuate.EventEntity;

@EventEntity(entity="com.mennyei.core.club.domain.ClubAggregate")
public interface ClubEvent extends Event {

}
