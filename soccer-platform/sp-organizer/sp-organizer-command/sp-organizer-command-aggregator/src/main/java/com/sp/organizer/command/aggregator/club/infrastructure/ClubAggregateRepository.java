package com.sp.organizer.command.aggregator.club.infrastructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sp.organizer.api.competition.ClubCommand;
import com.sp.organizer.command.aggregator.club.domain.ClubAggregate;

import io.eventuate.AggregateRepository;
import io.eventuate.EventuateAggregateStore;

@Repository
public class ClubAggregateRepository extends AggregateRepository<ClubAggregate, ClubCommand> {

	@Autowired
	public ClubAggregateRepository(EventuateAggregateStore aggregateStore) {
		super(ClubAggregate.class, aggregateStore);
	}

}
