package com.mennyei.core.team.infrastructure;

import com.mennyei.core.team.commands.ClubCommand;
import com.mennyei.core.team.domain.ClubAggregate;
import io.eventuate.AggregateRepository;
import io.eventuate.EventuateAggregateStore;

public class ClubRepository extends AggregateRepository<ClubAggregate, ClubCommand> {

	public ClubRepository(Class<ClubAggregate> clasz, EventuateAggregateStore aggregateStore) {
		super(clasz, aggregateStore);
	}

}
