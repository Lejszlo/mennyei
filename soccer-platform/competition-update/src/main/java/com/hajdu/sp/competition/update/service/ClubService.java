package com.hajdu.sp.competition.update.service;

import com.hajdu.sp.competition.update.command.club.CreateClub;
import com.hajdu.sp.competition.update.domain.ClubAggregate;
import com.hajdu.sp.competition.update.infrastructure.ClubAggregateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class ClubService {

    private final ClubAggregateRepository clubRepository;

    public String save(CreateClub createClub) throws ExecutionException, InterruptedException {
        return clubRepository.save(new CreateClub(createClub.getClubInfo()))
                .get()
                .getEntityId();
    }

    public Optional<ClubAggregate> find(String entityId) throws ExecutionException, InterruptedException {
		return Optional.ofNullable(clubRepository.find(entityId).get())
                .flatMap(e -> Optional.ofNullable(e.getEntity()));
	}

}
