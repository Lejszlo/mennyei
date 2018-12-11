package com.sp.organizer.command.aggregator.club.service;

import com.sp.organizer.api.command.club.AddPlayerClubCommand;
import com.sp.organizer.api.command.club.CreateClub;
import com.sp.organizer.api.command.club.RemovePlayerClubCommand;
import com.sp.organizer.command.aggregator.club.domain.ClubAggregate;
import com.sp.organizer.command.aggregator.club.infrastructure.ClubAggregateRepository;
import io.eventuate.EntityWithIdAndVersion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import value.Transfer;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class ClubService {

	@Autowired
	private ClubAggregateRepository clubRepository;

	public CompletableFuture<EntityWithIdAndVersion<ClubAggregate>> saveClub(CreateClub createClub) {
		return clubRepository.save(new CreateClub(createClub.getClubInfo()));
	}

	public void transferPlayer(Transfer transfer) throws ExecutionException, InterruptedException {
        clubRepository.update(transfer.getTargetTeamId(), new AddPlayerClubCommand(transfer.getTargetTeamId(), transfer.getPlayerId())).get();
        if(transfer.getSourceTeamId() != null) {
        	clubRepository.update(transfer.getSourceTeamId(), new RemovePlayerClubCommand(transfer.getTargetTeamId(), transfer.getPlayerId())).get();
    	}
	}

}
