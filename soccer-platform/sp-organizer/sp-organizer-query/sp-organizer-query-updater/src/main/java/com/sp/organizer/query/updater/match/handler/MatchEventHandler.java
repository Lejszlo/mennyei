package com.sp.organizer.query.updater.match.handler;

import com.sp.organizer.query.updater.club.entity.ClubQuery;
import com.sp.organizer.query.updater.club.repository.ClubQueryMongoRepository;
import com.sp.organizer.query.updater.match.entity.MatchQuery;
import sp.match.api.event.MatchAdded;
import sp.match.api.event.MatchPlayed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.eventuate.DispatchedEvent;
import io.eventuate.EventHandlerMethod;
import io.eventuate.EventSubscriber;
import sp.match.api.value.MatchInfo;
import com.sp.organizer.query.updater.match.repository.MatchQueryMongoRepository;

@EventSubscriber
@Component
public class MatchEventHandler {

	private final MatchQueryMongoRepository matchMongoRepository;
	
	private final ClubQueryMongoRepository clubQueryMongoRepository;

    @Autowired
    public MatchEventHandler(MatchQueryMongoRepository matchMongoRepository, ClubQueryMongoRepository clubQueryMongoRepository) {
        this.matchMongoRepository = matchMongoRepository;
        this.clubQueryMongoRepository = clubQueryMongoRepository;
    }

    @EventHandlerMethod
    public void matchAdded(DispatchedEvent<MatchAdded> dispatchedEvent) {
        MatchAdded matchAdded = dispatchedEvent.getEvent();
        String matchId = dispatchedEvent.getEntityId();
        MatchInfo matchInfo = matchAdded.getMatchInfo();
        ClubQuery homeClubQuery = clubQueryMongoRepository.findOne(matchInfo.getAwayClubId());
        ClubQuery awayClubQuery = clubQueryMongoRepository.findOne(matchInfo.getHomeClubId());
        MatchQuery matchQuery = MatchQuery.builder(matchId, homeClubQuery, awayClubQuery).build();
        matchQuery.setMatchDate(matchInfo.getMatchDate());
        matchQuery.setMatchResultDetailes(matchAdded.getMatchInfo().getMatchResultDetails());
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
//        competitionTableService.refreshTable(matchQuery);
    }
	
}
