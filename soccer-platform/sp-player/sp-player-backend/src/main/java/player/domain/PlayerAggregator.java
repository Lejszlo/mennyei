package player.domain;

import java.util.Arrays;
import java.util.List;


import com.sp.core.backend.event.player.PlayerAdded;
import com.sp.core.backend.value.player.Player;
import io.eventuate.Event;
import io.eventuate.ReflectiveMutableCommandProcessingAggregate;
import player.command.AddPlayerCommand;
import player.command.PlayerCommand;

/**
 * Created by lejsz on 2016. 11. 22..
 */
public class PlayerAggregator extends ReflectiveMutableCommandProcessingAggregate<PlayerAggregator, PlayerCommand> {

    private Player player;

    public List<Event> process(AddPlayerCommand addPlayerCommand) {
        return Arrays.asList(new PlayerAdded(addPlayerCommand.getPlayer()));
    }

    public void apply(PlayerAdded playerAdded) {
        player = playerAdded.getPlayer();
    }


}
