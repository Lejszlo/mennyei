package com.hajdu.sp.competition.update.infrastructure;

import com.hajdu.sp.competition.update.command.competition.CompetitionCommand;
import com.hajdu.sp.competition.update.domain.CompetitionAggregate;
import io.eventuate.AggregateRepository;
import io.eventuate.EventuateAggregateStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CompetitionAggregateRepository extends AggregateRepository<CompetitionAggregate, CompetitionCommand> {

	@Autowired
	public CompetitionAggregateRepository(EventuateAggregateStore aggregateStore) {
		super(CompetitionAggregate.class, aggregateStore);
	}

}
