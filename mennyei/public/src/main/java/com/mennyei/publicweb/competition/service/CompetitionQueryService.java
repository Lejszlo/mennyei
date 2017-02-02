package com.mennyei.publicweb.competition.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mennyei.publicweb.competition.dto.CompetitionQuery;
import com.mennyei.publicweb.competition.infrastructure.CompetitionMongoRepository;

@Service
public class CompetitionQueryService {
	
	@Autowired
	private CompetitionMongoRepository competitionMongoRepository;

	public Optional<CompetitionQuery> findCompetition(String competationId) {
		List<CompetitionQuery> findAll = competitionMongoRepository.findAll();
		if(findAll.isEmpty()) {
			return Optional.empty();
		}
		return Optional.of(findAll.get(0));
	}

	
	
}
