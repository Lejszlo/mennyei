package com.mennyei.core.competition.infrastructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mennyei.core.competition.commands.CompetitionCommand;
import com.mennyei.core.competition.domain.CompetitionAggregator;

import io.eventuate.AggregateRepository;
import io.eventuate.EventuateAggregateStore;

@Repository
public class CompetitionAggregateRepository extends AggregateRepository<CompetitionAggregator, CompetitionCommand> {

	@Autowired
	public CompetitionAggregateRepository(EventuateAggregateStore aggregateStore) {
		super(CompetitionAggregator.class, aggregateStore);
	}

}
