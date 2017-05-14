package com.sp.organizer.backend.competition.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sp.organizer.backend.club.domain.ClubQuery;
import com.sp.organizer.backend.club.infrastructure.ClubQueryMongoRepository;
import com.sp.organizer.backend.competition.dto.competition.CompetitionQuery;
import com.sp.organizer.backend.competition.infrastructure.CompetitionQueryMongoRepository;

@Service
public class CompetitionQueryService {
	
	@Autowired
	private CompetitionQueryMongoRepository competitionQueryMongoRepository;
	
	@Autowired
	private ClubQueryMongoRepository clubQueryMongoRepository;

	public Optional<CompetitionQuery> findCompetition(String competationId) {
		List<CompetitionQuery> findAll = competitionQueryMongoRepository.findAll();
		if(findAll.isEmpty()) {
			return Optional.empty();
		}
		return Optional.of(findAll.get(0));
	}

	public List<CompetitionQuery> findCompetitionsByClub(String clubId) {
		ClubQuery clubQuery = clubQueryMongoRepository.findOne(clubId);
		return competitionQueryMongoRepository.findByClubsIn(clubQuery);
	}
	
}
