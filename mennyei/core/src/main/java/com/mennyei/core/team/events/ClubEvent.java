package com.mennyei.core.team.events;

import io.eventuate.Event;
import io.eventuate.EventEntity;

@EventEntity(entity="com.mennyei.core.team.domain.ClubAggregate")
public interface ClubEvent extends Event {

}
