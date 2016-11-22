package com.mennyei.core.club.domain;

import com.mennyei.core.club.commands.AddClubCommand;
import com.mennyei.core.club.commands.ClubCommand;
import com.mennyei.core.club.events.ClubAdded;
import com.mennyei.core.club.events.PlayerAddedToClub;
import com.mennyei.core.club.events.PlayerRemovedFromClub;
import io.eventuate.Event;
import io.eventuate.EventUtil;
import io.eventuate.ReflectiveMutableCommandProcessingAggregate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class ClubAggregate extends ReflectiveMutableCommandProcessingAggregate<ClubAggregate, ClubCommand> {

	private ClubInfo clubInfo;

	private Set<String> playerIds = new HashSet<>();

	public List<Event> process(AddClubCommand addClubCommand) {
		return EventUtil.events(ClubAdded.builder().clubInfo(addClubCommand.getClubInfo()).build());
	}
	
	public void apply(ClubAdded clubAdded) {
		clubInfo = clubAdded.getClubInfo();
	}

	public void apply(PlayerAddedToClub playerAddedToClubEvent) {
		playerIds.add(playerAddedToClubEvent.getTransfer().getPlayerId());
	}

	public void apply(PlayerRemovedFromClub playerRemovedFromClub) {
		playerIds.remove(playerRemovedFromClub.getTransfer().getPlayerId());
	}
}
