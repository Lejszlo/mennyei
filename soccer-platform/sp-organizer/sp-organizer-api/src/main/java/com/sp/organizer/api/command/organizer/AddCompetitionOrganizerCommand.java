package com.sp.organizer.api.command.organizer;

import com.sp.organizer.api.value.competition.CompetitionId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@Builder
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class AddCompetitionOrganizerCommand extends OrganizerCommand{

    CompetitionId competitionId;

}
