package com.mennyei.core.match.infrastructure;

import com.mennyei.core.match.command.MatchCommand;
import com.mennyei.core.match.domain.MatchAggregator;

import io.eventuate.AggregateRepository;
import io.eventuate.EventuateAggregateStore;

public class MatchAggregateRepository extends AggregateRepository<MatchAggregator, MatchCommand> {

	public MatchAggregateRepository(Class<MatchAggregator> clasz, EventuateAggregateStore aggregateStore) {
		super(clasz, aggregateStore);
	}

}
