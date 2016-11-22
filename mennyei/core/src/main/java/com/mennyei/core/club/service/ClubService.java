package com.mennyei.core.club.service;

import com.mennyei.core.club.commands.AddClubCommand;
import com.mennyei.core.club.domain.ClubInfo;
import com.mennyei.core.club.domain.ClubAggregate;
import com.mennyei.core.club.infrastructure.ClubRepository;
import io.eventuate.EntityWithIdAndVersion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class ClubService {

	@Autowired
	private ClubRepository clubRepository;

	public CompletableFuture<EntityWithIdAndVersion<ClubAggregate>> addClub(ClubInfo clubInfo) {
		return clubRepository.save(AddClubCommand.builder().clubInfo(clubInfo).build());
	}

}
