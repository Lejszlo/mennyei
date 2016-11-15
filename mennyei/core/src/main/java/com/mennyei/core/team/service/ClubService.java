package com.mennyei.core.team.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mennyei.core.team.commands.AddClubCommand;
import com.mennyei.core.team.domain.Club;
import com.mennyei.core.team.domain.ClubAggregate;
import com.mennyei.core.team.infrastructure.ClubRepository;

import io.eventuate.EntityWithIdAndVersion;

@Service
public class ClubService {

	@Autowired
	private ClubRepository clubRepository;

	public CompletableFuture<EntityWithIdAndVersion<ClubAggregate>> registerClub(Club club) {
		return clubRepository.save(AddClubCommand.builder().club(club).build());
	}

}
