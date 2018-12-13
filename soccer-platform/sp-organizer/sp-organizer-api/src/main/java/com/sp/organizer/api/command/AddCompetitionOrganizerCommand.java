package com.sp.organizer.api.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;
import sp.competition.api.value.CompetitionId;

@Value
@Builder
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class AddCompetitionOrganizerCommand extends OrganizerCommand{

    CompetitionId competitionId;

}
