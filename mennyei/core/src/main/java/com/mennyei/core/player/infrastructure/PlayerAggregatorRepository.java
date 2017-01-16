package com.mennyei.core.player.infrastructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mennyei.core.player.command.PlayerCommand;
import com.mennyei.core.player.domain.PlayerAggregator;

import io.eventuate.AggregateRepository;
import io.eventuate.EventuateAggregateStore;

/**
 * Created by lejsz on 2016. 11. 22..
 */
@Repository
public class PlayerAggregatorRepository extends AggregateRepository<PlayerAggregator, PlayerCommand> {

    @Autowired
    public PlayerAggregatorRepository(EventuateAggregateStore aggregateStore) {
        super(PlayerAggregator.class, aggregateStore);
    }
}
