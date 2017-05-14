package com.sp.organizer.backend.club.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.sp.organizer.backend.club.commands.AddPlayerToClubCommand;
import com.sp.organizer.backend.club.commands.ClubCommand;
import com.sp.organizer.backend.club.commands.RemovePlayerFromClub;
import com.sp.organizer.backend.club.commands.AddClubCommand;
import com.sp.core.backend.value.club.ClubInfo;

import com.sp.core.backend.event.organizer.club.ClubAdded;
import com.sp.core.backend.event.player.PlayerAddedToClub;
import com.sp.core.backend.event.player.PlayerRemovedFromClub;
import io.eventuate.Event;
import io.eventuate.EventUtil;
import io.eventuate.ReflectiveMutableCommandProcessingAggregate;


public class ClubAggregate extends ReflectiveMutableCommandProcessingAggregate<ClubAggregate, ClubCommand> {

    private ClubInfo clubInfo;

    private Set<String> playerIds = new HashSet<>();

    public List<Event> process(AddClubCommand addClubCommand) {
        return EventUtil.events(new ClubAdded(addClubCommand.getClubInfo()));
    }

    public List<Event> process(AddPlayerToClubCommand addPlayerToClubCommand) {
        return EventUtil.events(new PlayerAddedToClub(addPlayerToClubCommand.getClubId(), addPlayerToClubCommand.getPlayerId()));
    }

    public List<Event> process(RemovePlayerFromClub removePlayerFromClub) {
        return EventUtil.events(PlayerRemovedFromClub.builder().playerId(removePlayerFromClub.getPlayerId()).build());
    }

    public void apply(ClubAdded clubAdded) {
        clubInfo = clubAdded.getClubInfo();
    }

    public void apply(PlayerAddedToClub playerAddedToClubEvent) {
        playerIds.add(playerAddedToClubEvent.getPlayerId());
    }

    public void apply(PlayerRemovedFromClub playerRemovedFromClub) {
        playerIds.remove(playerRemovedFromClub.getPlayerId());
    }
}
