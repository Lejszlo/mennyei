package com.hajdu.sp.club.update.aggregator.infrastructure;

import com.hajdu.sp.club.lib.command.ClubCommand;
import io.eventuate.AggregateRepository;
import io.eventuate.EventuateAggregateStore;
import com.hajdu.sp.club.update.aggregator.domain.ClubAggregate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ClubAggregateRepository extends AggregateRepository<ClubAggregate, ClubCommand> {

	@Autowired
	public ClubAggregateRepository(EventuateAggregateStore aggregateStore) {
		super(ClubAggregate.class, aggregateStore);
	}

}
