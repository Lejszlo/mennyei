package com.sp.competition.command.aggregator.infrastructure;

import com.sp.competition.command.aggregator.domain.CompetitionAggregate;
import io.eventuate.AggregateRepository;
import io.eventuate.EventuateAggregateStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import sp.competition.api.command.CompetitionCommand;

@Repository
public class CompetitionAggregateRepository extends AggregateRepository<CompetitionAggregate, CompetitionCommand> {

	@Autowired
	public CompetitionAggregateRepository(EventuateAggregateStore aggregateStore) {
		super(CompetitionAggregate.class, aggregateStore);
	}

}
