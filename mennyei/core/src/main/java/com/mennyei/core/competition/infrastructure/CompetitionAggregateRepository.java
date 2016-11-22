package com.mennyei.core.competition.infrastructure;

import com.mennyei.core.competition.commands.CompetitionCommand;
import com.mennyei.core.competition.domain.CompetitionAggregator;
import io.eventuate.AggregateRepository;
import io.eventuate.EventuateAggregateStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CompetitionAggregateRepository extends AggregateRepository<CompetitionAggregator, CompetitionCommand> {

	@Autowired
	public CompetitionAggregateRepository(EventuateAggregateStore aggregateStore) {
		super(CompetitionAggregator.class, aggregateStore);
	}

}
