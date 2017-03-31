package com.mennyei.publicweb.match.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mennyei.publicweb.match.dto.detailes.MatchDetailesResource;
import com.mennyei.publicweb.match.dto.detailes.MatchDetailesResourceAssemblerSupport;
import com.mennyei.publicweb.match.dto.match.MatchQuery;
import com.mennyei.publicweb.match.infrastructure.MatchQueryMongoRepository;

@Service
public class MatchDetailesService {

	@Autowired
	private MatchQueryMongoRepository matchMongoRepository;
	
	public MatchDetailesResource matchDetailes(String matchId) {
		MatchQuery matchQuery = matchMongoRepository.findOne(matchId);
		MatchDetailesResourceAssemblerSupport matchResourceDetailesAssemblerSupport = new MatchDetailesResourceAssemblerSupport();
		MatchDetailesResource detailesResource = matchResourceDetailesAssemblerSupport.toResource(matchQuery);
		return detailesResource;
	}
	
}
