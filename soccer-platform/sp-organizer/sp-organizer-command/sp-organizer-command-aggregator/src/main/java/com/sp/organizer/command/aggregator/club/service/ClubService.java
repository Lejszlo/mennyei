package com.sp.organizer.command.aggregator.club.service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import com.sp.organizer.api.command.club.CreateClubCommand;
import com.sp.organizer.command.aggregator.club.domain.ClubAggregate;
import com.sp.organizer.command.aggregator.club.infrastructure.ClubAggregateRepository;
import com.sp.organizer.api.command.club.AddPlayerClubCommand;
import com.sp.organizer.api.command.club.RemovePlayerClubCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.eventuate.EntityWithIdAndVersion;
import value.Transfer;
import com.sp.organizer.api.value.club.ClubInfo;

@Service
public class ClubService {

	@Autowired
	private ClubAggregateRepository clubRepository;

	public CompletableFuture<EntityWithIdAndVersion<ClubAggregate>> addClub(ClubInfo clubInfo) {
		return clubRepository.save(new CreateClubCommand(clubInfo));
	}

	public void transferPlayer(Transfer transfer) throws ExecutionException, InterruptedException {
        clubRepository.update(transfer.getTargetTeamId(), new AddPlayerClubCommand(transfer.getTargetTeamId(), transfer.getPlayerId())).get();
        if(transfer.getSourceTeamId() != null) {
        	clubRepository.update(transfer.getSourceTeamId(), new RemovePlayerClubCommand(transfer.getTargetTeamId(), transfer.getPlayerId())).get();
    	}
	}

}
