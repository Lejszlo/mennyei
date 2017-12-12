package sp.match.api.event;

import io.eventuate.Event;
import io.eventuate.EventEntity;

@EventEntity(entity="com.sp.organizer.backend.match.domain.MatchAggregator")
public interface MatchEvents extends Event {

}
