package com.sp.organizer.backend.club.infrastructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sp.organizer.backend.club.commands.ClubCommand;
import com.sp.organizer.backend.club.domain.ClubAggregate;

import io.eventuate.AggregateRepository;
import io.eventuate.EventuateAggregateStore;

@Repository
public class ClubAggregateRepository extends AggregateRepository<ClubAggregate, ClubCommand> {

	@Autowired
	public ClubAggregateRepository(EventuateAggregateStore aggregateStore) {
		super(ClubAggregate.class, aggregateStore);
	}

}
