package com.sp.organizer.api.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import sp.competition.api.value.CompetitionId;

@Value
@Builder
@AllArgsConstructor
public class CompetitionAdded implements OrganizerEvent {

    CompetitionId competitionId;

}
