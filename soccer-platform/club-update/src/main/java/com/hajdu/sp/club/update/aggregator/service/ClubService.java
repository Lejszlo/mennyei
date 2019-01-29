package com.hajdu.sp.club.update.aggregator.service;

import com.hajdu.sp.club.lib.command.CreateClub;
import io.eventuate.EntityWithIdAndVersion;
import com.hajdu.sp.club.update.aggregator.domain.ClubAggregate;
import com.hajdu.sp.club.update.aggregator.infrastructure.ClubAggregateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class ClubService {

	@Autowired
	private ClubAggregateRepository clubRepository;

	public CompletableFuture<EntityWithIdAndVersion<ClubAggregate>> saveClub(CreateClub createClub) {
		return clubRepository.save(new CreateClub(createClub.getClubInfo()));
	}

}
