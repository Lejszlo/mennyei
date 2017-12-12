package event.competition;

import io.eventuate.Event;
import io.eventuate.EventEntity;

@EventEntity(entity="com.sp.organizer.backend.competition.domain.CompetitionAggregate")
public interface CompetitionEvent extends Event {

}
