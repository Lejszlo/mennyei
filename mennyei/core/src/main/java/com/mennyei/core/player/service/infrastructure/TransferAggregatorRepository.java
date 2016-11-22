package com.mennyei.core.player.service.infrastructure;

import com.mennyei.core.transfer.command.TransferCommand;
import com.mennyei.core.transfer.domain.TransferAggregator;
import io.eventuate.AggregateRepository;
import io.eventuate.EventuateAggregateStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by lejsz on 2016. 11. 22..
 */
@Repository
public class TransferAggregatorRepository extends AggregateRepository<TransferAggregator, TransferCommand> {

    @Autowired
    public TransferAggregatorRepository(EventuateAggregateStore aggregateStore) {
        super(TransferAggregator.class, aggregateStore);
    }
}
