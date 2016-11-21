package com.mennyei.core.team.domain;

import com.mennyei.core.team.commands.AddClubCommand;
import com.mennyei.core.team.commands.ClubCommand;
import com.mennyei.core.team.events.ClubAdded;
import io.eventuate.Event;
import io.eventuate.EventUtil;
import io.eventuate.ReflectiveMutableCommandProcessingAggregate;

import java.util.List;


public class ClubAggregate extends ReflectiveMutableCommandProcessingAggregate<ClubAggregate, ClubCommand> {

	private String fullName;

	private String shortName;

	public List<Event> process(AddClubCommand addClubCommand) {
		return EventUtil.events(ClubAdded.builder().club(addClubCommand.getClub()).build());
	}
	
	public void apply(ClubAdded clubAdded) {}
}
