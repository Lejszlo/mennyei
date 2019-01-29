package com.hajdu.sp.competition.lib.event;


import io.eventuate.Event;
import io.eventuate.EventEntity;

@EventEntity(entity="com.hajdu.sp.competition.update.aggregator.domain.CompetitionAggregate")
public interface CompetitionEvent extends Event {

}
