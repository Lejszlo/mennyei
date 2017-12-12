package com.sp.organizer.command.aggregator.club.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import command.competition.AddPlayerToClubCommand;
import command.competition.ClubCommand;
import command.competition.RemovePlayerFromClub;
import command.competition.AddClubCommand;

import event.PlayerAddedToClub;
import event.PlayerRemovedFromClub;
import event.club.ClubAdded;
import io.eventuate.Event;
import io.eventuate.EventUtil;
import io.eventuate.ReflectiveMutableCommandProcessingAggregate;
import value.club.ClubInfo;

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
