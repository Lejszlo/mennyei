package com.mennyei.core.match.infrastructure;

import org.springframework.stereotype.Repository;

import com.mennyei.core.match.command.MatchCommand;
import com.mennyei.core.match.domain.MatchAggregator;

import io.eventuate.AggregateRepository;
import io.eventuate.EventuateAggregateStore;

@Repository
public class MatchAggregateRepository extends AggregateRepository<MatchAggregator, MatchCommand> {

	public MatchAggregateRepository(EventuateAggregateStore aggregateStore) {
		super(MatchAggregator.class, aggregateStore);
	}

}
