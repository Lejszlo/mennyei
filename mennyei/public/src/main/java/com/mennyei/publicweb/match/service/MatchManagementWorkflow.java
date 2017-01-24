package com.mennyei.publicweb.match.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mennyei.core.match.domain.MatchInfo;
import com.mennyei.core.match.event.MatchAdded;
import com.mennyei.core.match.event.MatchPlayed;
import com.mennyei.core.match.event.MatchSet;
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
        matchQuery.setAwayClub(clubQueryMongoRepository.findOne(matchInfo.getAwayClubId()));
        matchQuery.setHomeClub(clubQueryMongoRepository.findOne(matchInfo.getHomeClubId()));
        matchQuery.setStageName(matchInfo.getStageName());
        matchQuery.setCompetition(competitionMongoRepository.findOne(matchInfo.getCompetitionId()));
        matchQuery.setIndex(matchInfo.getIndex());
        matchMongoRepository.save(matchQuery);
    }
    
    @EventHandlerMethod
    public void matchSet(DispatchedEvent<MatchSet> dispatchedEvent) {
    	MatchSet matchPlayed = dispatchedEvent.getEvent();
        String matchId = dispatchedEvent.getEntityId();
        MatchQuery matchQuery = matchMongoRepository.findOne(matchId);
        matchQuery.setAwayLineUps(matchPlayed.getAwayLineUps());
        matchQuery.setHomeLineUps(matchPlayed.getHomeLineUps());
        matchMongoRepository.save(matchQuery);
    }
    
    @EventHandlerMethod
    public void matchPlayed(DispatchedEvent<MatchPlayed> dispatchedEvent) {
    	MatchPlayed matchPlayed = dispatchedEvent.getEvent();
        String matchId = dispatchedEvent.getEntityId();
        MatchQuery matchQuery = matchMongoRepository.findOne(matchId);
        MatchInfo matchInfo = matchPlayed.getMatchInfo();
        if(matchInfo.isPlayed()) {
        	matchQuery.setPlayed(true);
        	matchQuery.setFans(matchInfo.getFans());
        	matchQuery.setMatchResultType(matchInfo.getResultGoals().whoIsTheWinner());
        	matchQuery.setHomeGoalAmount(matchInfo.getResultGoals().getHomeGoalAmount());
        	matchQuery.setAwayGoalAmount(matchInfo.getResultGoals().getAwayGoalAmount());
        }
        matchQuery = matchMongoRepository.save(matchQuery);
        competitionTableService.refreshTable(matchQuery);
    }
	
}
