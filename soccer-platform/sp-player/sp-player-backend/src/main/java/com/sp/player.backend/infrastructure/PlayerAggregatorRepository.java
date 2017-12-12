package com.sp.player.backend.infrastructure;

import com.sp.player.backend.command.PlayerCommand;
import com.sp.player.backend.domain.PlayerAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import io.eventuate.AggregateRepository;
import io.eventuate.EventuateAggregateStore;

@Repository
public class PlayerAggregatorRepository extends AggregateRepository<PlayerAggregator, PlayerCommand> {

    @Autowired
    public PlayerAggregatorRepository(EventuateAggregateStore aggregateStore) {
        super(PlayerAggregator.class, aggregateStore);
    }
}
