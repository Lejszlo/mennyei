package com.sp.match.api.event;

import io.eventuate.Event;
import io.eventuate.EventEntity;

@EventEntity(entity="com.sp.match.command.aggregator.domain.MatchAggregator")
public interface MatchEvent extends Event {

}
