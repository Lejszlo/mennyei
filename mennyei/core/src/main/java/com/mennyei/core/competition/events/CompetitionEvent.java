package com.mennyei.core.competition.events;

import io.eventuate.Event;
import io.eventuate.EventEntity;

@EventEntity(entity="com.mennyei.core.competition.domain.CompetitionAggregator")
public interface CompetitionEvent extends Event {

}
