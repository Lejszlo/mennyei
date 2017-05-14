package player.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sp.core.backend.value.player.Player;

import io.eventuate.EntityWithIdAndVersion;
import player.command.AddPlayerCommand;
import player.domain.PlayerAggregator;
import player.infrastructure.PlayerAggregatorRepository;

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
