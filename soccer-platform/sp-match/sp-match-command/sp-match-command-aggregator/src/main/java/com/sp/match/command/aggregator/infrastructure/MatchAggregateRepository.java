package com.sp.match.command.aggregator.infrastructure;

import com.sp.match.api.command.MatchCommand;
import com.sp.match.command.aggregator.domain.MatchAggregator;
import io.eventuate.AggregateRepository;
import io.eventuate.EventuateAggregateStore;
import org.springframework.stereotype.Repository;

@Repository
public class MatchAggregateRepository extends AggregateRepository<MatchAggregator, MatchCommand> {

	public MatchAggregateRepository(EventuateAggregateStore aggregateStore) {
		super(MatchAggregator.class, aggregateStore);
	}

}
