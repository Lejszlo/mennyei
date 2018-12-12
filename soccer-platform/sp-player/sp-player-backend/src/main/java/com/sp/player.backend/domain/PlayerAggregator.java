package com.sp.player.backend.domain;

import com.sp.player.backend.command.AddPlayerCommand;
import com.sp.player.backend.command.PlayerCommand;
import event.PlayerAdded;
import io.eventuate.Event;
import io.eventuate.ReflectiveMutableCommandProcessingAggregate;
import value.Player;

import java.util.Arrays;
import java.util.List;

public class PlayerAggregator extends ReflectiveMutableCommandProcessingAggregate<PlayerAggregator, PlayerCommand> {

    private Player player;

    public List<Event> process(AddPlayerCommand addPlayerCommand) {
        return Arrays.asList(new PlayerAdded(addPlayerCommand.getPlayer()));
    }

    public void apply(PlayerAdded playerAdded) {
        player = playerAdded.getPlayer();
    }


}
