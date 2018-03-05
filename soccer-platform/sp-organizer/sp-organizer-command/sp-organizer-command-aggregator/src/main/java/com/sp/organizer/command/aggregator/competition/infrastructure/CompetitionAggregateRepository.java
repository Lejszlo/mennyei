package com.sp.organizer.command.aggregator.competition.infrastructure;

import com.sp.organizer.command.aggregator.competition.domain.CompetitionAggregate;
import com.sp.organizer.api.competition.CompetitionCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import io.eventuate.AggregateRepository;
import io.eventuate.EventuateAggregateStore;

@Repository
public class CompetitionAggregateRepository extends AggregateRepository<CompetitionAggregate, CompetitionCommand> {

	@Autowired
	public CompetitionAggregateRepository(EventuateAggregateStore aggregateStore) {
		super(CompetitionAggregate.class, aggregateStore);
	}

}
