package com.mennyei.core.team.infrastructure;

import org.springframework.stereotype.Repository;

import com.mennyei.core.team.commands.ClubCommand;
import com.mennyei.core.team.domain.ClubAggregate;

import io.eventuate.AggregateRepository;
import io.eventuate.EventuateAggregateStore;

@Repository
public class ClubRepository extends AggregateRepository<ClubAggregate, ClubCommand> {

	public ClubRepository(Class<ClubAggregate> clasz, EventuateAggregateStore aggregateStore) {
		super(clasz, aggregateStore);
	}

}
