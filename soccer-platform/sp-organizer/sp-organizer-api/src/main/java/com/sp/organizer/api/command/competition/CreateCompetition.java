package com.sp.organizer.api.command.competition;

import lombok.*;
import com.sp.organizer.api.value.competition.CompetitionInfo;

@Value
@Builder
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
public class CreateCompetition extends CompetitionCommand {
	
	@NonNull
	private CompetitionInfo competitionInfo;
}
