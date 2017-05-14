package com.sp.organizer.backend.competition.infrastructure;

import com.sp.organizer.backend.competition.command.CompetitionCommand;
import com.sp.organizer.backend.competition.domain.Competition;
import com.sp.organizer.backend.competition.domain.CompetitionAggregate;
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
