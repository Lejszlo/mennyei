package sp.club.command.aggregator.infrastructure;

import com.sp.club.api.command.ClubCommand;
import io.eventuate.AggregateRepository;
import io.eventuate.EventuateAggregateStore;
import sp.club.command.aggregator.domain.ClubAggregate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ClubAggregateRepository extends AggregateRepository<ClubAggregate, ClubCommand> {

	@Autowired
	public ClubAggregateRepository(EventuateAggregateStore aggregateStore) {
		super(ClubAggregate.class, aggregateStore);
	}

}
