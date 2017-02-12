package com.mennyei.publicweb.match.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mennyei.core.match.domain.event.MatchEventType;
import com.mennyei.publicweb.match.dto.MatchDetailesResource;
import com.mennyei.publicweb.match.dto.MatchQuery;
import com.mennyei.publicweb.match.dto.MatchResourceDetailesAssemblerSupport;
import com.mennyei.publicweb.match.infrastructure.MatchQueryMongoRepository;

@Service
public class MatchDetailesService {

	@Autowired
	private MatchQueryMongoRepository matchMongoRepository;
	
	public MatchDetailesResource matchDetailes(String matchId) {
		MatchQuery matchQuery = matchMongoRepository.findOne(matchId);
		MatchResourceDetailesAssemblerSupport matchResourceDetailesAssemblerSupport = new MatchResourceDetailesAssemblerSupport();
		MatchDetailesResource detailesResource = matchResourceDetailesAssemblerSupport.toResource(matchQuery);
		
		detailesResource.setTotalRedCardAmount(matchQuery.getMatchResultDetailes().getEvents(Arrays.asList(MatchEventType.RED_CARD)).size());
		detailesResource.setTotalYellowCardAmount(matchQuery.getMatchResultDetailes().getEvents(Arrays.asList(MatchEventType.YELLOW_CARD)).size());
		
		return detailesResource;
	}
	
}
