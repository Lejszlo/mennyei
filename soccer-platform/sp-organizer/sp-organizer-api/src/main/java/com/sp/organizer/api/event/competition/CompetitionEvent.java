package com.sp.organizer.api.event.competition;

import io.eventuate.Event;
import io.eventuate.EventEntity;

@EventEntity(entity="com.sp.organizer.command.aggregator.competition.domain.CompetitionAggregate")
public interface CompetitionEvent extends Event {

}
