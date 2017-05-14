package transfer.infrastructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import io.eventuate.AggregateRepository;
import io.eventuate.EventuateAggregateStore;
import transfer.command.TransferCommand;
import transfer.domain.TransferAggregator;

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
