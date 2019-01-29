package com.hajdu.sp.competition.lib.event;

import com.hajdu.sp.competition.lib.value.season.Turn;
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
