package com.mennyei.core.competition.infrastructure;

import com.mennyei.core.competition.commands.CompetitionCommand;
import com.mennyei.core.competition.domain.CompetitionAggregator;

import io.eventuate.AggregateRepository;
import io.eventuate.EventuateAggregateStore;

public class CompetitionAggregateRepository extends AggregateRepository<CompetitionAggregator, CompetitionCommand> {

	public CompetitionAggregateRepository(Class<CompetitionAggregator> clasz, EventuateAggregateStore aggregateStore) {
		super(clasz, aggregateStore);
	}

}
