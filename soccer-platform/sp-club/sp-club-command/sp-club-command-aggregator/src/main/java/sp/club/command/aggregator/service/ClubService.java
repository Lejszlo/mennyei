package sp.club.command.aggregator.service;

import com.sp.club.api.command.AddPlayerClubCommand;
import com.sp.club.api.command.CreateClub;
import com.sp.club.api.command.RemovePlayerClubCommand;
import io.eventuate.EntityWithIdAndVersion;
import sp.club.command.aggregator.domain.ClubAggregate;
import sp.club.command.aggregator.infrastructure.ClubAggregateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class ClubService {

	@Autowired
	private ClubAggregateRepository clubRepository;

	public CompletableFuture<EntityWithIdAndVersion<ClubAggregate>> saveClub(CreateClub createClub) {
		return clubRepository.save(new CreateClub(createClub.getClubInfo()));
	}

}
