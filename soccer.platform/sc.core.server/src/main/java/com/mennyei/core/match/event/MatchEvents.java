package com.mennyei.core.match.event;

import io.eventuate.Event;
import io.eventuate.EventEntity;

@EventEntity(entity="com.mennyei.core.match.domain.MatchAggregator")
public interface MatchEvents extends Event {

}
