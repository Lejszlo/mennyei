package com.mennyei.core.player.domain;

import com.mennyei.core.player.command.AddPlayerCommand;
import com.mennyei.core.player.command.PlayerCommand;
import com.mennyei.core.common.commands.PlayerAdded;
import io.eventuate.Event;
import io.eventuate.ReflectiveMutableCommandProcessingAggregate;

import java.util.Arrays;
import java.util.List;

/**
 * Created by lejsz on 2016. 11. 22..
 */
public class PlayerAggregator extends ReflectiveMutableCommandProcessingAggregate<PlayerAggregator, PlayerCommand> {

    private Player player;

    public List<Event> process(AddPlayerCommand addPlayerCommand) {
        return Arrays.asList(PlayerAdded.builder().player(addPlayerCommand.getPlayer()).build());
    }

    public void apply(PlayerAdded playerAdded) {
        player = playerAdded.getPlayer();
    }


}
