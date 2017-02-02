package com.mennyei.publicweb.match.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mennyei.core.match.domain.MatchInfo;
import com.mennyei.core.match.event.MatchAdded;
import com.mennyei.core.match.event.MatchPlayed;
import com.mennyei.core.match.event.MatchSet;
import com.mennyei.publicweb.club.dto.ClubQuery;
import com.mennyei.publicweb.club.infrastructure.ClubQueryMongoRepository;
import com.mennyei.publicweb.competition.dto.CompetitionQuery;
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
        MatchInfo matchInfo = matchAdded.getMatchInfo();
        ClubQuery homeClubQuery = clubQueryMongoRepository.findOne(matchInfo.getAwayClubId());
        ClubQuery awayClubQuery = clubQueryMongoRepository.findOne(matchInfo.getHomeClubId());
        CompetitionQuery competitionQuery = competitionMongoRepository.findOne(matchInfo.getCompetitionId());
        MatchQuery matchQuery = MatchQuery.builder(matchId, homeClubQuery, awayClubQuery, competitionQuery).build();
        matchQuery.setMatchDate(matchInfo.getMatchDate());
        matchQuery.setStageName(matchInfo.getStageName());
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
        if(matchPlayed.isPlayed()) {
        	matchQuery.setPlayed(true);
        	matchQuery.setFans(matchPlayed.getFans());
        	matchQuery.setMatchResultType(matchPlayed.getMatchResultDetailes().whoIsTheWinner());
        	matchQuery.setHomeGoalAmount(matchPlayed.getMatchResultDetailes().getHomeGoalAmount());
        	matchQuery.setAwayGoalAmount(matchPlayed.getMatchResultDetailes().getAwayGoalAmount());
        }
        matchQuery = matchMongoRepository.save(matchQuery);
        competitionTableService.refreshTable(matchQuery);
    }
	
}
