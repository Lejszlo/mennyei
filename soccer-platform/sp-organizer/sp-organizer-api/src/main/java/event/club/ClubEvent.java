package event.club;

import io.eventuate.Event;
import io.eventuate.EventEntity;

@EventEntity(entity="com.sp.organizer.backend.club.domain.ClubAggregate")
public interface ClubEvent extends Event {

}
