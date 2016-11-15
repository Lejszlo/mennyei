package com.mennyei.core.team.domain;

import java.util.List;

import com.mennyei.core.team.commands.ClubCommand;
import com.mennyei.core.team.commands.AddClubCommand;
import com.mennyei.core.team.events.ClubAdded;

import io.eventuate.Event;
import io.eventuate.EventUtil;
import io.eventuate.ReflectiveMutableCommandProcessingAggregate;

public class ClubAggregate extends ReflectiveMutableCommandProcessingAggregate<ClubAggregate, ClubCommand> {
	
	public List<Event> process(AddClubCommand registerClubCommand) {
		return EventUtil.events(ClubAdded.builder().club(registerClubCommand.getClub()).build());
	}
}
