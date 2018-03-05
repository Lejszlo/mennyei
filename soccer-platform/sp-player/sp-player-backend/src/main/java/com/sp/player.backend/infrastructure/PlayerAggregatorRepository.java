package com.sp.player.backend.infrastructure;

import com.sp.player.backend.command.PlayerCommand;
import com.sp.player.backend.domain.PlayerAggregator;


import io.eventuate.AggregateRepository;
import io.eventuate.EventuateAggregateStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PlayerAggregatorRepository extends AggregateRepository<PlayerAggregator, PlayerCommand> {

    @Autowired
    public PlayerAggregatorRepository(EventuateAggregateStore aggregateStore) {
        super(PlayerAggregator.class, aggregateStore);
    }
}
