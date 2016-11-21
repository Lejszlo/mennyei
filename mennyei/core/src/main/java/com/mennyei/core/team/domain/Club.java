package com.mennyei.core.team.domain;

import com.mennyei.core.team.commands.ClubCommand;
import io.eventuate.ReflectiveMutableCommandProcessingAggregate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class Club extends ReflectiveMutableCommandProcessingAggregate<Club, ClubCommand> {
	
	private String fullName;

	private String shortName;
}
