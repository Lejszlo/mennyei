package com.sp.organizer.api.event.competition;

import com.sp.organizer.api.value.competition.season.Turn;
import lombok.*;

import java.util.Collection;
import java.util.UUID;

@Builder
@Value
@AllArgsConstructor
public class TurnsAdded implements CompetitionEvent {

    @NonNull
    private UUID stageId;

    @Singular
    private Collection<Turn> turns;
}
