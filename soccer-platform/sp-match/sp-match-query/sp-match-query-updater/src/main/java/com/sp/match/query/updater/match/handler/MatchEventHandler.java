package com.sp.match.query.updater.match.handler;

import com.sp.match.query.updater.match.entity.MatchDocument;
import com.sp.match.api.event.MatchAdded;
import com.sp.match.api.event.MatchPlayed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.eventuate.DispatchedEvent;
import io.eventuate.EventHandlerMethod;
import io.eventuate.EventSubscriber;
import com.sp.match.api.value.MatchInfo;
import com.sp.match.query.updater.match.repository.MatchDocumentMongoRepository;

@EventSubscriber
@Component
public class MatchEventHandler {

	private final MatchDocumentMongoRepository matchMongoRepository;
	
    @Autowired
    public MatchEventHandler(MatchDocumentMongoRepository matchMongoRepository) {
        this.matchMongoRepository = matchMongoRepository;
    }

    @EventHandlerMethod
    public void matchAdded(DispatchedEvent<MatchAdded> dispatchedEvent) {
        MatchAdded matchAdded = dispatchedEvent.getEvent();
        String matchId = dispatchedEvent.getEntityId();
        MatchInfo matchInfo = matchAdded.getMatchInfo();
        MatchDocument matchDocument = MatchDocument.builder(matchId, matchInfo.getHomeClubId(), matchInfo.getAwayClubId()).build();
        matchDocument.setMatchDate(matchInfo.getMatchDate());
        matchDocument.setMatchResultDetails(matchAdded.getMatchInfo().getMatchResultDetails());
        matchMongoRepository.save(matchDocument);
    }
    
	@EventHandlerMethod
    public void matchPlayed(DispatchedEvent<MatchPlayed> dispatchedEvent) {
    	MatchPlayed matchPlayed = dispatchedEvent.getEvent();
        String matchId = dispatchedEvent.getEntityId();
        MatchDocument matchDocument = matchMongoRepository.findOne(matchId);
        if(matchPlayed.isPlayed()) {
        	matchDocument.setPlayed(true);
        	matchDocument.setFans(matchPlayed.getFans());
        	matchDocument.setMatchResultDetails(matchPlayed.getMatchResultDetailes());
        }
        matchMongoRepository.save(matchDocument);
    }
	
}
