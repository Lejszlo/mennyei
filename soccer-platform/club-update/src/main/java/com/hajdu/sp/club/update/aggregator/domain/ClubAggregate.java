package com.hajdu.sp.club.update.aggregator.domain;

import com.hajdu.sp.club.lib.command.AddPlayerClubCommand;
import com.hajdu.sp.club.lib.command.ClubCommand;
import com.hajdu.sp.club.lib.command.CreateClub;
import com.hajdu.sp.club.lib.command.RemovePlayerClubCommand;
import com.hajdu.sp.club.lib.event.ClubCreated;
import com.hajdu.sp.club.lib.event.PlayerAddedToClub;
import com.hajdu.sp.club.lib.event.PlayerRemovedFromClub;
import com.hajdu.sp.club.lib.value.ClubInfo;
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
