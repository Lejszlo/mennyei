package com.mennyei.util.player.service;

import com.sp.core.backend.DateUtil;
import com.sp.core.backend.event.match.MatchPlayed;
import com.sp.core.backend.event.player.PlayerAdded;
import com.sp.core.backend.value.player.Player;
import com.mennyei.util.player.domain.PlayerQuery;
import com.mennyei.util.player.infrastructure.PlayerQueryMongoRepository;
import io.eventuate.DispatchedEvent;
import io.eventuate.EventHandlerMethod;
import io.eventuate.EventSubscriber;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Created by lejsz on 2016. 11. 28..
 */
@EventSubscriber
@Component
public class PlayerEventSubscriber {

    @Autowired
    private PlayerQueryMongoRepository playerQueryMongoRepository;

    @Autowired
    private PlayerMatchStatisticService playerMatchStatisticService;
    
    @Autowired
    private ModelMapper modelMapper;

    @EventHandlerMethod
    public void create(DispatchedEvent<PlayerAdded> dispatchedEvent) {
        PlayerAdded event = dispatchedEvent.getEvent();
        String playerId = dispatchedEvent.getEntityId();
        Player player = event.getPlayer();
        PlayerQuery playerQuery = PlayerQuery.builder()
                .id(playerId)
                .age(Long.valueOf(LocalDate.parse(player.getBirthday(), DateUtil.dateTimeFormatterShort).until(LocalDate.now(), ChronoUnit.YEARS)).intValue())
                .build();
        modelMapper.map(player, playerQuery);
        playerQueryMongoRepository.save(playerQuery);
    }

    @EventHandlerMethod
    public void matchPlayed(DispatchedEvent<MatchPlayed> dispatchedEvent) {
        MatchPlayed matchPlayed = dispatchedEvent.getEvent();
        String matchId = dispatchedEvent.getEntityId();
        playerMatchStatisticService.updatePlayerStatistics(matchId, matchPlayed.getMatchResultDetailes(), matchPlayed.getCompetitionId());
    }

}
