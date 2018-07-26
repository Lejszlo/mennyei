package com.sp.organizer.api.command.competition;

import lombok.*;
import com.sp.organizer.api.value.competition.CompetitionInfo;

@Value
@Builder
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
public class CreateCompetitionCommand extends CompetitionCommand {
	
	@NonNull
	private CompetitionInfo competitionInfo;
}
