package com.sp.organizer.backend.match.service;

import com.sp.core.backend.value.match.MatchInfo;
import com.sp.core.backend.event.match.MatchAdded;
import com.sp.core.backend.event.match.MatchPlayed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sp.organizer.backend.club.domain.ClubQuery;
import com.sp.organizer.backend.club.infrastructure.ClubQueryMongoRepository;
import com.sp.organizer.backend.competition.dto.competition.CompetitionQuery;
import com.sp.organizer.backend.competition.infrastructure.CompetitionQueryMongoRepository;
import com.sp.organizer.backend.competition.service.CompetitionTableService;
import com.sp.organizer.backend.match.dto.match.MatchQuery;
import com.sp.organizer.backend.match.infrastructure.MatchQueryMongoRepository;

import io.eventuate.DispatchedEvent;
import io.eventuate.EventHandlerMethod;
import io.eventuate.EventSubscriber;

@EventSubscriber
@Component
public class MatchManagementSubscriber {

	@Autowired
	private MatchQueryMongoRepository matchMongoRepository;
	
	@Autowired
	private ClubQueryMongoRepository clubQueryMongoRepository;
	
	@Autowired
	private CompetitionTableService competitionTableService;
	
	@Autowired
	private CompetitionQueryMongoRepository competitionMongoRepository;
	
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
    }
	
}
