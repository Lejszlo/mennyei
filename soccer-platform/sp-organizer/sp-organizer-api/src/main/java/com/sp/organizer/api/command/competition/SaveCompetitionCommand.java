package com.sp.organizer.api.command.competition;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.Value;
import com.sp.organizer.api.value.competition.CompetitionInfo;

@Value
@Builder
@EqualsAndHashCode(callSuper=false)
public class SaveCompetitionCommand extends CompetitionCommand {
	
	@NonNull
	private CompetitionInfo competitionInfo;
}
