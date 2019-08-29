package com.hajdu.sp.competition.update.domain;

import com.hajdu.sp.competition.update.command.club.AddPlayeTorClub;
import com.hajdu.sp.competition.update.command.club.ClubCommand;
import com.hajdu.sp.competition.update.command.club.CreateClub;
import com.hajdu.sp.competition.update.command.club.RemovePlayerToClub;
import com.hajdu.sp.competition.update.event.club.ClubCreated;
import com.hajdu.sp.competition.update.event.club.PlayerAddedToClub;
import com.hajdu.sp.competition.update.event.club.PlayerRemovedFromClub;
import com.hajdu.sp.competition.update.value.club.ClubInfo;
import io.eventuate.Event;
import io.eventuate.EventUtil;
import io.eventuate.ReflectiveMutableCommandProcessingAggregate;
import lombok.Getter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
public class ClubAggregate extends ReflectiveMutableCommandProcessingAggregate<ClubAggregate, ClubCommand> {
    private ClubInfo clubInfo;
    private Set<String> playerIds = new HashSet<>();

    public List<Event> process(CreateClub createClub) {
        return EventUtil.events(new ClubCreated(createClub.getClubInfo()));
    }

    public List<Event> process(AddPlayeTorClub addPlayeTorClub) {
        return EventUtil.events(new PlayerAddedToClub(addPlayeTorClub.getClubId(), addPlayeTorClub.getPlayerId()));
    }

    public List<Event> process(RemovePlayerToClub removePlayerToClub) {
        return EventUtil.events(PlayerRemovedFromClub.builder().playerId(removePlayerToClub.getPlayerId()).build());
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
