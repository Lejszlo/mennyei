package sp.competition.api.event;

import io.eventuate.Event;
import io.eventuate.EventEntity;

@EventEntity(entity="com.sp.competition.command.aggregator.domain.CompetitionAggregate")
public interface CompetitionEvent extends Event {

}
