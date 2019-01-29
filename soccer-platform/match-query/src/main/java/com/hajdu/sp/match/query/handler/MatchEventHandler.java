package com.hajdu.sp.match.query.handler;

import com.hajdu.sp.match.lib.event.MatchAdded;
import com.hajdu.sp.match.lib.value.MatchInfo;
import com.hajdu.sp.match.query.entity.MatchDocument;
import com.hajdu.sp.match.query.repository.MatchDocumentMongoRepository;
import io.eventuate.DispatchedEvent;
import io.eventuate.EventHandlerMethod;
import io.eventuate.EventSubscriber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
        matchMongoRepository.save(matchDocument);
    }
    
}
