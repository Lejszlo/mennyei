package com.hajdu.sp.competition.update.command.competition;

import com.hajdu.sp.competition.update.value.competition.organizer.Organizer;
import lombok.*;

@Value
@EqualsAndHashCode(callSuper=false)
@Builder
public class CreateCompetition extends CompetitionCommand {
	@NonNull
    Organizer organizer;
}
