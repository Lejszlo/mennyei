package com.sp.organizer.backend.club.service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import com.sp.organizer.backend.club.commands.AddPlayerToClubCommand;
import com.sp.organizer.backend.club.commands.RemovePlayerFromClub;
import com.sp.core.backend.value.player.Transfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sp.organizer.backend.club.commands.AddClubCommand;
import com.sp.organizer.backend.club.domain.ClubAggregate;
import com.sp.core.backend.value.club.ClubInfo;
import com.sp.organizer.backend.club.infrastructure.ClubAggregateRepository;

import io.eventuate.EntityWithIdAndVersion;

@Service
public class ClubService {

	@Autowired
	private ClubAggregateRepository clubRepository;

	public CompletableFuture<EntityWithIdAndVersion<ClubAggregate>> addClub(ClubInfo clubInfo) {
		return clubRepository.save(new AddClubCommand(clubInfo));
	}

	public void transferPlayer(Transfer transfer) throws ExecutionException, InterruptedException {
        clubRepository.update(transfer.getTargetTeamId(), new AddPlayerToClubCommand(transfer.getTargetTeamId(), transfer.getPlayerId())).get();
        if(transfer.getSourceTeamId() != null) {
        	clubRepository.update(transfer.getSourceTeamId(), new RemovePlayerFromClub(transfer.getTargetTeamId(), transfer.getPlayerId())).get();
    	}
	}

}
