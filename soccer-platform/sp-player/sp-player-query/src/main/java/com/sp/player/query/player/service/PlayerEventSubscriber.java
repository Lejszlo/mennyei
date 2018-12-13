package com.sp.player.query.player.service;

import com.sp.player.query.player.domain.PlayerQuery;
import com.sp.player.query.player.infrastructure.PlayerQueryMongoRepository;
import event.PlayerAdded;
import io.eventuate.DispatchedEvent;
import io.eventuate.EventHandlerMethod;
import io.eventuate.EventSubscriber;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import value.Player;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@EventSubscriber
@Component
public class PlayerEventSubscriber {
    public static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");
    public static DateTimeFormatter dateTimeFormatterShort = DateTimeFormatter.ofPattern("yyyy.MM.dd");

    @Autowired
    private PlayerQueryMongoRepository playerQueryMongoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @EventHandlerMethod
    public void create(DispatchedEvent<PlayerAdded> dispatchedEvent) {
        PlayerAdded event = dispatchedEvent.getEvent();
        String playerId = dispatchedEvent.getEntityId();
        Player player = event.getPlayer();
        PlayerQuery playerQuery = PlayerQuery.builder()
                .id(playerId)
                .age(Long.valueOf(LocalDate.parse(player.getBirthday(), dateTimeFormatterShort).until(LocalDate.now(), ChronoUnit.YEARS)).intValue())
                .build();
        modelMapper.map(player, playerQuery);
        playerQueryMongoRepository.save(playerQuery);
    }

}
