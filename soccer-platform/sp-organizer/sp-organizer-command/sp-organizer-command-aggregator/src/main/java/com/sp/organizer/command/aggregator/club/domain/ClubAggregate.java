package com.sp.organizer.command.aggregator.club.domain;

import com.sp.organizer.api.command.club.AddPlayerClubCommand;
import com.sp.organizer.api.command.club.ClubCommand;
import com.sp.organizer.api.command.club.CreateClub;
import com.sp.organizer.api.command.club.RemovePlayerClubCommand;
import com.sp.organizer.api.event.club.ClubCreated;
import com.sp.organizer.api.value.club.ClubInfo;
import event.PlayerAddedToClub;
import event.PlayerRemovedFromClub;
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
