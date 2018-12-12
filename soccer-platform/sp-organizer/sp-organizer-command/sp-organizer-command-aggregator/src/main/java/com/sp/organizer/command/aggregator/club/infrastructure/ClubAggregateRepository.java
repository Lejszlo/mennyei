package com.sp.organizer.command.aggregator.club.infrastructure;

import com.sp.organizer.api.command.club.ClubCommand;
import com.sp.organizer.command.aggregator.club.domain.ClubAggregate;
import io.eventuate.AggregateRepository;
import io.eventuate.EventuateAggregateStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ClubAggregateRepository extends AggregateRepository<ClubAggregate, ClubCommand> {

	@Autowired
	public ClubAggregateRepository(EventuateAggregateStore aggregateStore) {
		super(ClubAggregate.class, aggregateStore);
	}

}
