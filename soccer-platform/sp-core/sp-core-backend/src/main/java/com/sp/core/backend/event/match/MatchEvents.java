package com.sp.core.backend.event.match;

import io.eventuate.Event;
import io.eventuate.EventEntity;

@EventEntity(entity="MatchAggregator")
public interface MatchEvents extends Event {

}
