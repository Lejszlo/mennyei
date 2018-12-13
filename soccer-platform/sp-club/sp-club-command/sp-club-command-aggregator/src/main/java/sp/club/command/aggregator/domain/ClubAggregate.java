package sp.club.command.aggregator.domain;

import com.sp.club.api.command.AddPlayerClubCommand;
import com.sp.club.api.command.ClubCommand;
import com.sp.club.api.command.CreateClub;
import com.sp.club.api.command.RemovePlayerClubCommand;
import com.sp.club.api.event.ClubCreated;
import com.sp.club.api.value.ClubInfo;
import com.sp.club.api.event.PlayerAddedToClub;
import com.sp.club.api.event.PlayerRemovedFromClub;
import io.eventuate.Event;
import io.eventuate.EventUtil;
import io.eventuate.ReflectiveMutableCommandProcessingAggregate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ClubAggregate extends ReflectiveMutableCommandProcessingAggregate<ClubAggregate, ClubCommand> {

    private ClubInfo clubInfo;

    private Set<String> playerIds = new HashSet<>();

    public List<Event> process(CreateClub createClub) {
        return EventUtil.events(new ClubCreated(createClub.getClubInfo()));
    }

    public List<Event> process(AddPlayerClubCommand addPlayerClubCommand) {
        return EventUtil.events(new PlayerAddedToClub(addPlayerClubCommand.getClubId(), addPlayerClubCommand.getPlayerId()));
    }

    public List<Event> process(RemovePlayerClubCommand removePlayerClubCommand) {
        return EventUtil.events(PlayerRemovedFromClub.builder().playerId(removePlayerClubCommand.getPlayerId()).build());
    }

    public void apply(ClubCreated clubCreated) {
        clubInfo = clubCreated.getClubInfo();
    }

    public void apply(PlayerAddedToClub playerAddedToClubEvent) {
        playerIds.add(playerAddedToClubEvent.getPlayerId());
    }

    public void apply(PlayerRemovedFromClub playerRemovedFromClub) {
        playerIds.remove(playerRemovedFromClub.getPlayerId());
    }
}
