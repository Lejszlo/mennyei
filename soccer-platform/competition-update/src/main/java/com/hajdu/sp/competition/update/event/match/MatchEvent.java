package com.hajdu.sp.competition.update.event.match;

import io.eventuate.Event;
import io.eventuate.EventEntity;

@EventEntity(entity="com.hajdu.sp.competition.update.domain.MatchAggregator")
public interface MatchEvent extends Event {

}
