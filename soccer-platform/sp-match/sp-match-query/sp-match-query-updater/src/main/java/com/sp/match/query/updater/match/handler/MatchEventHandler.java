package com.sp.match.query.updater.match.handler;

import com.sp.match.api.event.MatchAdded;
import com.sp.match.api.value.MatchInfo;
import com.sp.match.query.updater.match.entity.MatchDocument;
import com.sp.match.query.updater.match.repository.MatchDocumentMongoRepository;
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
