package com.mennyei.publicweb.match.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mennyei.core.match.domain.MatchInfo;
import com.mennyei.core.match.domain.ResultGoals;
import com.mennyei.core.match.event.MatchAdded;
import com.mennyei.core.match.event.MatchPlayed;
import com.mennyei.publicweb.club.infrastructure.ClubQueryMongoRepository;
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
	private ClubQueryMongoRepository clubQueryMongoRepository;
	
	@Autowired
	private CompetitionTableService competitionTableService;
	
	@Autowired
	private CompetitionMongoRepository competitionMongoRepository;
	
    @EventHandlerMethod
    public void matchAdded(DispatchedEvent<MatchAdded> dispatchedEvent) {
        MatchAdded matchAdded = dispatchedEvent.getEvent();
        String matchId = dispatchedEvent.getEntityId();
        MatchQuery matchQuery = MatchQuery.builder(matchId).build();
        MatchInfo matchInfo = matchAdded.getMatchInfo();
        matchQuery.setMatchDate(matchInfo.getMatchDate());
        matchQuery.setAwayClubId(clubQueryMongoRepository.findOne(matchInfo.getAwayClubId()));
        matchQuery.setHomeClubId(clubQueryMongoRepository.findOne(matchInfo.getHomeClubId()));
        matchQuery.setStageName(matchInfo.getStageName());
        matchQuery.setCompetition(competitionMongoRepository.findOne(matchInfo.getCompetitionId()));
        matchQuery.setIndex(matchInfo.getIndex());
        matchMongoRepository.save(matchQuery);
    }
    
    @EventHandlerMethod
    public void matchPlayed(DispatchedEvent<MatchPlayed> dispatchedEvent) {
    	MatchPlayed matchPlayed = dispatchedEvent.getEvent();
        String matchId = dispatchedEvent.getEntityId();
        MatchQuery matchQuery = matchMongoRepository.findOne(matchId);
        MatchInfo matchInfo = matchPlayed.getMatchInfo();
        ResultGoals result = matchInfo.getResultGoals();
        if(result != null) {
        	matchQuery.setPlayed(true);
        	matchQuery.setFans(matchInfo.getFans());
        	matchQuery.setResultGoals(matchInfo.getResultGoals());
        	matchQuery.setHomeGoalAmount(matchInfo.getGoalAmountFor(matchInfo.getHomeClubId()));
        	matchQuery.setAwayGoalAmount(matchInfo.getGoalAmountFor(matchInfo.getAwayClubId()));
        }
        competitionTableService.refreshTable(matchQuery, matchPlayed);
        competitionMongoRepository.save(matchQuery.getCompetition());
    }
	
}
