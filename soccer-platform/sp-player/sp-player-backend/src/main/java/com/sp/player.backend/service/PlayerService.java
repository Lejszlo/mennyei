package com.sp.player.backend.service;

import java.util.concurrent.CompletableFuture;

import com.sp.player.backend.command.AddPlayerCommand;
import com.sp.player.backend.domain.PlayerAggregator;
import com.sp.player.backend.infrastructure.PlayerAggregatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.eventuate.EntityWithIdAndVersion;
import value.Player;

@Service
public class PlayerService {

    @Autowired
    private PlayerAggregatorRepository playerAggregatorRepository;

    public CompletableFuture<EntityWithIdAndVersion<PlayerAggregator>> addPlayer(Player player) {
        return playerAggregatorRepository.save(AddPlayerCommand.builder().player(player).build());
    }

}
