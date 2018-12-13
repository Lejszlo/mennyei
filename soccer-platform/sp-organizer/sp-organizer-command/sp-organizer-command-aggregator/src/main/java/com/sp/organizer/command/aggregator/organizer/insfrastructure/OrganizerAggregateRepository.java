package com.sp.organizer.command.aggregator.organizer.insfrastructure;

import com.sp.organizer.api.command.OrganizerCommand;
import com.sp.organizer.command.aggregator.organizer.domain.OrganizerAggregate;
import io.eventuate.AggregateRepository;
import io.eventuate.EventuateAggregateStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OrganizerAggregateRepository extends AggregateRepository<OrganizerAggregate, OrganizerCommand> {

	@Autowired
	public OrganizerAggregateRepository(EventuateAggregateStore aggregateStore) {
		super(OrganizerAggregate.class, aggregateStore);
	}

}
