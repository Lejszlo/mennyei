package com.hajdu.sp.competition.update.service;

import com.hajdu.sp.competition.update.command.club.CreateClub;
import com.hajdu.sp.competition.update.domain.ClubAggregate;
import com.hajdu.sp.competition.update.infrastructure.ClubAggregateRepository;
import io.eventuate.EntityWithIdAndVersion;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class ClubService {

	private final ClubAggregateRepository clubRepository;

	public CompletableFuture<EntityWithIdAndVersion<ClubAggregate>> saveClub(CreateClub createClub) {
		return clubRepository.save(new CreateClub(createClub.getClubInfo()));
	}

}
