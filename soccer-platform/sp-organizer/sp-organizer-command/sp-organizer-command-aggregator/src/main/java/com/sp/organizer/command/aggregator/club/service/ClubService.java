package com.sp.organizer.command.aggregator.club.service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import com.sp.organizer.command.aggregator.club.domain.ClubAggregate;
import com.sp.organizer.command.aggregator.club.infrastructure.ClubAggregateRepository;
import command.competition.AddPlayerToClubCommand;
import command.competition.RemovePlayerFromClub;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import command.competition.AddClubCommand;

import io.eventuate.EntityWithIdAndVersion;
import value.Transfer;
import value.club.ClubInfo;

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
