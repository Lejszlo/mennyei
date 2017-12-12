package com.sp.match.backend.infrastucture;

import sp.match.api.command.MatchCommand;
import com.sp.match.backend.domain.MatchAggregator;
import io.eventuate.AggregateRepository;
import io.eventuate.EventuateAggregateStore;
import org.springframework.stereotype.Repository;

@Repository
public class MatchAggregateRepository extends AggregateRepository<MatchAggregator, MatchCommand> {

	public MatchAggregateRepository(EventuateAggregateStore aggregateStore) {
		super(MatchAggregator.class, aggregateStore);
	}

}
