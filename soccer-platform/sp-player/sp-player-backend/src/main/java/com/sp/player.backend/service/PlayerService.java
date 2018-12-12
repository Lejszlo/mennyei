package com.sp.player.backend.service;

import com.sp.player.backend.command.AddPlayerCommand;
import com.sp.player.backend.domain.PlayerAggregator;
import com.sp.player.backend.infrastructure.PlayerAggregatorRepository;
import io.eventuate.EntityWithIdAndVersion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import value.Player;

import java.util.concurrent.CompletableFuture;

@Service
public class PlayerService {

    @Autowired
    private PlayerAggregatorRepository playerAggregatorRepository;

    public CompletableFuture<EntityWithIdAndVersion<PlayerAggregator>> addPlayer(Player player) {
        return playerAggregatorRepository.save(AddPlayerCommand.builder().player(player).build());
    }

}
