package com.hajdu.sp.competition.lib.command;

import lombok.*;
import com.hajdu.sp.competition.lib.value.CompetitionInfo;

@Value
@Builder
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
public class CreateCompetition extends CompetitionCommand {
	
	@NonNull
	private CompetitionInfo competitionInfo;
}
