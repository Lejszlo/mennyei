package com.hajdu.sp.competition.update.event.competition;


import io.eventuate.Event;
import io.eventuate.EventEntity;

@EventEntity(entity="com.hajdu.sp.competition.update.domain.CompetitionAggregate")
public interface CompetitionEvent extends Event {

}
