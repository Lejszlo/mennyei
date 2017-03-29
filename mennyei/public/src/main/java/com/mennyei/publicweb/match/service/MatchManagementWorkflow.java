package com.mennyei.publicweb.match.service;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mennyei.core.match.domain.MatchInfo;
import com.mennyei.core.match.domain.event.lineup.LineUp;
import com.mennyei.core.match.event.MatchAdded;
import com.mennyei.core.match.event.MatchPlayed;
import com.mennyei.core.match.event.MatchSet;
import com.mennyei.publicweb.club.dto.ClubQuery;
import com.mennyei.publicweb.club.dto.PlayerQuery;
import com.mennyei.publicweb.club.infrastructure.ClubQueryMongoRepository;
import com.mennyei.publicweb.club.infrastructure.PlayerQueryMongoRepository;
import com.mennyei.publicweb.club.service.PlayerMatchStatisticService;
import com.mennyei.publicweb.competition.dto.CompetitionQuery;
import com.mennyei.publicweb.competition.infrastructure.CompetitionQueryMongoRepository;
import com.mennyei.publicweb.competition.service.CompetitionTableService;
import com.mennyei.publicweb.match.dto.LineUpQuery;
import com.mennyei.publicweb.match.dto.MatchQuery;
import com.mennyei.publicweb.match.infrastructure.MatchQueryMongoRepository;

import io.eventuate.DispatchedEvent;
import io.eventuate.EventHandlerMethod;
import io.eventuate.EventSubscriber;

@EventSubscriber
@Component
public class MatchManagementWorkflow {

	@Autowired
	private MatchQueryMongoRepository matchMongoRepository;
	
	@Autowired
	private ClubQueryMongoRepository clubQueryMongoRepository;
	
	@Autowired
	private CompetitionTableService competitionTableService;
	
	@Autowired
	private CompetitionQueryMongoRepository competitionMongoRepository;
	
	@Autowired
	private PlayerQueryMongoRepository playerQueryMongoRepository;
	
	@Autowired
	private PlayerMatchStatisticService playerMatchStatisticService;
	
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
        matchQuery.setMatchResultDetailes(matchAdded.getMatchInfo().getMatchResultDetailes());
        matchQuery.setIndex(matchInfo.getIndex());
        matchMongoRepository.save(matchQuery);
    }
    
    @EventHandlerMethod
    public void matchSet(DispatchedEvent<MatchSet> dispatchedEvent) {
    	MatchSet matchPlayed = dispatchedEvent.getEvent();
        String matchId = dispatchedEvent.getEntityId();
        MatchQuery matchQuery = matchMongoRepository.findOne(matchId);
        matchQuery.setAwayLineUps(matchPlayed.getAwayLineUps().stream().map(this::createLineUpQuery).collect(Collectors.toList()));
        matchQuery.setHomeLineUps(matchPlayed.getHomeLineUps().stream().map(this::createLineUpQuery).collect(Collectors.toList()));
        matchMongoRepository.save(matchQuery);
    }

	private LineUpQuery createLineUpQuery(LineUp lineUp) {
		LineUpQuery lineUpQuery = new LineUpQuery(); 
		lineUpQuery.setLineUpType(lineUp.getLineUpType());
		lineUpQuery.setShirtNumber(lineUp.getShirtNumber());
		PlayerQuery playerQuery = playerQueryMongoRepository.findOne(lineUp.getPlayerId());
		playerQuery.setNumber(lineUp.getShirtNumber());
		playerQueryMongoRepository.save(playerQuery);
		lineUpQuery.setPlayerQuery(playerQuery);
		return lineUpQuery;
	}
    
	@EventHandlerMethod
    public void matchPlayed(DispatchedEvent<MatchPlayed> dispatchedEvent) {
    	MatchPlayed matchPlayed = dispatchedEvent.getEvent();
        String matchId = dispatchedEvent.getEntityId();
        MatchQuery matchQuery = matchMongoRepository.findOne(matchId);
        if(matchPlayed.isPlayed()) {
        	matchQuery.setPlayed(true);
        	matchQuery.setFans(matchPlayed.getFans());
        	matchQuery.setMatchResultDetailes(matchPlayed.getMatchResultDetailes());
        }
        matchQuery = matchMongoRepository.save(matchQuery);
        competitionTableService.refreshTable(matchQuery);
        playerMatchStatisticService.updatePlayerStatistics(matchQuery);
    }
	
}
