package com.mennyei.core.player.events;

import io.eventuate.Event;
import io.eventuate.EventEntity;

/**
 * Created by lejsz on 2016. 11. 22..
 */
@EventEntity(entity="com.mennyei.core.player.domain.PlayerAggregator")
public interface PlayerEvent extends Event {
}
