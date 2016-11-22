package com.mennyei.core.club.infrastructure;

import com.mennyei.core.club.commands.ClubCommand;
import com.mennyei.core.club.domain.ClubAggregate;
import io.eventuate.AggregateRepository;
import io.eventuate.EventuateAggregateStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ClubRepository extends AggregateRepository<ClubAggregate, ClubCommand> {

	@Autowired
	public ClubRepository(EventuateAggregateStore aggregateStore) {
		super(ClubAggregate.class, aggregateStore);
	}

}
