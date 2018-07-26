package com.sp.organizer.api.event.organizer;

import com.sp.organizer.api.value.competition.CompetitionId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
public class CompetitionAdded implements OrganizerEvent {

    CompetitionId competitionId;

}
