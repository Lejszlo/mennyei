package com.sp.organizer.api.command.competition;

import com.sp.organizer.api.value.competition.season.Turn;
import lombok.*;

@Value
@Builder
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class AddMatchCompetitionCommand extends CompetitionCommand {
	@NonNull
	String competitionId;
	
	@NonNull
	String stageName;

	@NonNull
	Turn turn;
}
