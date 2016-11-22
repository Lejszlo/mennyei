package com.mennyei.core.player.service;

import com.mennyei.core.player.command.AddPlayerCommand;
import com.mennyei.core.player.domain.Player;
import com.mennyei.core.player.domain.PlayerAggregator;
import com.mennyei.core.player.infrastructure.PlayerAggregatorRepository;
import io.eventuate.EntityWithIdAndVersion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

/**
 * Created by lejsz on 2016. 11. 22..
 */
@Service
public class PlayerService {

    @Autowired
    private PlayerAggregatorRepository playerAggregatorRepository;

    public CompletableFuture<EntityWithIdAndVersion<PlayerAggregator>> addPlayer(Player player) {
        return playerAggregatorRepository.save(AddPlayerCommand.builder().player(player).build());
    }

}
