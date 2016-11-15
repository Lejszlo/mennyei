package com.mennyei.core.team.domain;

import java.util.List;

import com.mennyei.core.team.commands.ClubCommand;
import com.mennyei.core.team.commands.RegisterClubCommand;
import com.mennyei.core.team.events.ClubRegistered;

import io.eventuate.Event;
import io.eventuate.EventUtil;
import io.eventuate.ReflectiveMutableCommandProcessingAggregate;

public class ClubAggregate extends ReflectiveMutableCommandProcessingAggregate<ClubAggregate, ClubCommand> {
	
	public List<Event> process(RegisterClubCommand registerClubCommand) {
		return EventUtil.events(ClubRegistered.builder().club(registerClubCommand.getClub()).build());
	}
}
