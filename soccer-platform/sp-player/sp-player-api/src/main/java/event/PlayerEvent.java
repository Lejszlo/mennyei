package event;

import io.eventuate.Event;
import io.eventuate.EventEntity;

@EventEntity(entity="com.sp.player.backend.domain.PlayerAggregator")
public interface PlayerEvent extends Event {
}
