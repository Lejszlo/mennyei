package com.hajdu.sp.competition.update.event.club;

import io.eventuate.Event;
import io.eventuate.EventEntity;

@EventEntity(entity="com.hajdu.sp.competition.update.domain.ClubAggregate")
public interface ClubEvent extends Event {

}
