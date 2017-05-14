package com.sp.organizer.backend.match.infrastructure;

import com.sp.organizer.backend.match.command.MatchCommand;
import com.sp.organizer.backend.match.domain.MatchAggregator;
import org.springframework.stereotype.Repository;

import io.eventuate.AggregateRepository;
import io.eventuate.EventuateAggregateStore;

@Repository
public class MatchAggregateRepository extends AggregateRepository<MatchAggregator, MatchCommand> {

	public MatchAggregateRepository(EventuateAggregateStore aggregateStore) {
		super(MatchAggregator.class, aggregateStore);
	}

}
