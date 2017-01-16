package com.mennyei.publicweb.competition.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mennyei.publicweb.match.dto.MatchDetailesQuery;
import com.mennyei.publicweb.match.dto.MatchQuery;
import com.mennyei.publicweb.match.infrastructure.MatchMongoRepository;

@Service
public class CompetitionMatchService {

	@Autowired
	private MatchMongoRepository matchMongoRepository;

	@Autowired
	private ModelMapper modelMapper;

	public MatchDetailesQuery getCompetationMatchDetailes(String matchId) {
		MatchQuery matchQuery = matchMongoRepository.findOne(matchId);
		MatchDetailesQuery matchDetailesQuery = MatchDetailesQuery.builder(matchQuery).build();
		// matchDetailesQuery.setTotalRedCardAmount(match.calculateTotalRedCardAmount());
		// matchDetailesQuery.setTotalYellowCardAmount(match.calculateTotalYellowCardAmount());
		return matchDetailesQuery;
	}

}
