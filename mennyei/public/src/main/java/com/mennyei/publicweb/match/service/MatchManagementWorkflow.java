package com.mennyei.publicweb.match.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mennyei.core.match.event.MatchAdded;
import com.mennyei.core.match.event.MatchPlayed;
import com.mennyei.publicweb.competition.infrastructure.CompetitionMongoRepository;
import com.mennyei.publicweb.competition.service.CompetitionTableService;
import com.mennyei.publicweb.match.dto.MatchQuery;
import com.mennyei.publicweb.match.infrastructure.MatchMongoRepository;

import io.eventuate.DispatchedEvent;
import io.eventuate.EventHandlerMethod;
import io.eventuate.EventSubscriber;

@EventSubscriber
@Component
public class MatchManagementWorkflow {

	@Autowired
	private MatchMongoRepository matchMongoRepository;
	
	@Autowired
	private CompetitionTableService competitionTableService;
	
	@Autowired
	private CompetitionMongoRepository competitionMongoRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
    @EventHandlerMethod
    public void matchAdded(DispatchedEvent<MatchAdded> dispatchedEvent) {
        MatchAdded matchAdded = dispatchedEvent.getEvent();
        String matchId = dispatchedEvent.getEntityId();
        MatchQuery matchQuery = MatchQuery.builder(matchId).build();
        modelMapper.map(matchAdded.getMatchInfo(), matchQuery);
        matchMongoRepository.save(matchQuery);
    }
    
    @EventHandlerMethod
    public void matchPlayed(DispatchedEvent<MatchPlayed> dispatchedEvent) {
    	MatchPlayed matchPlayed = dispatchedEvent.getEvent();
        String matchId = dispatchedEvent.getEntityId();
        MatchQuery matchQuery = matchMongoRepository.findOne(matchId);
        competitionTableService.refreshTable(matchQuery, matchPlayed);
        competitionMongoRepository.save(matchQuery.getCompetition());
    }
	
}
