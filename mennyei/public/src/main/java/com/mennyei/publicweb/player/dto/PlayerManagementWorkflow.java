package com.mennyei.publicweb.player.dto;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mennyei.core.DateUtil;
import com.mennyei.core.common.commands.PlayerAdded;
import com.mennyei.core.player.domain.Player;
import com.mennyei.publicweb.club.dto.PlayerQuery;
import com.mennyei.publicweb.club.infrastructure.PlayerQueryMongoRepository;

import io.eventuate.DispatchedEvent;
import io.eventuate.EventHandlerMethod;
import io.eventuate.EventSubscriber;

/**
 * Created by lejsz on 2016. 11. 28..
 */
@EventSubscriber
@Component
public class PlayerManagementWorkflow {

    @Autowired
    private PlayerQueryMongoRepository playerQueryMongoRepository;

    @EventHandlerMethod
    public void create(DispatchedEvent<PlayerAdded> dispatchedEvent) {
        PlayerAdded event = dispatchedEvent.getEvent();
        String playerId = dispatchedEvent.getEntityId();
        Player player = event.getPlayer();
        PlayerQuery playerQuery = PlayerQuery.builder()
                .id(playerId)
                .name(player.getName())
                .birthday(player.getBirthday())
                .age(Long.valueOf(LocalDate.parse(player.getBirthday(), DateUtil.dateTimeFormatter).until(LocalDate.now(), ChronoUnit.YEARS)).intValue())
                .number(player.getNumber())
                .build();
        playerQueryMongoRepository.save(playerQuery);
    }
}
