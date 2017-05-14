package player.infrastructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import io.eventuate.AggregateRepository;
import io.eventuate.EventuateAggregateStore;
import player.command.PlayerCommand;
import player.domain.PlayerAggregator;

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
