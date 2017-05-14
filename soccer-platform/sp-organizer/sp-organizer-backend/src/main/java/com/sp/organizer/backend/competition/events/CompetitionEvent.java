package com.sp.organizer.backend.competition.events;

import io.eventuate.Event;
import io.eventuate.EventEntity;

@EventEntity(entity="CompetitionAggregator")
public interface CompetitionEvent extends Event {

}
