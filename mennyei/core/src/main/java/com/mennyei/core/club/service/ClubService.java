package com.mennyei.core.club.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mennyei.core.club.commands.AddClubCommand;
import com.mennyei.core.club.domain.ClubAggregate;
import com.mennyei.core.club.domain.ClubInfo;
import com.mennyei.core.club.infrastructure.ClubAggregateRepository;

import io.eventuate.EntityWithIdAndVersion;

@Service
public class ClubService {

	@Autowired
	private ClubAggregateRepository clubRepository;

	public CompletableFuture<EntityWithIdAndVersion<ClubAggregate>> addClub(ClubInfo clubInfo) {
		return clubRepository.save(AddClubCommand.builder().clubInfo(clubInfo).build());
	}

}
