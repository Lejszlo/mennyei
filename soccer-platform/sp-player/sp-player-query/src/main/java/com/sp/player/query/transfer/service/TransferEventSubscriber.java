package com.sp.player.query.transfer.service;

import com.sp.match.api.value.lineup.LineUp;
import com.sp.player.query.player.domain.PlayerQuery;
import com.sp.player.query.player.domain.lineup.LineUpQuery;
import com.sp.player.query.player.infrastructure.PlayerQueryMongoRepository;
import io.eventuate.EventSubscriber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@EventSubscriber
@Component
public class TransferEventSubscriber {

    @Autowired
    private PlayerQueryMongoRepository playerQueryMongoRepository;

//    @EventHandlerMethod
//    public void matchSet(DispatchedEvent<MatchSet> dispatchedEvent) {
//        MatchSet matchPlayed = dispatchedEvent.getEvent();
//        String matchId = dispatchedEvent.getEntityId();
//        MatchQuery matchQuery = matchMongoRepository.findOne(matchId);
//        matchQuery.setAwayLineUps(matchPlayed.getAwayLineUps().stream().map(this::createLineUpQuery).collect(Collectors.toList()));
//        matchQuery.setHomeLineUps(matchPlayed.getHomeLineUps().stream().map(this::createLineUpQuery).collect(Collectors.toList()));
//        matchMongoRepository.save(matchQuery);
//    }

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

}
