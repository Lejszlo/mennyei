package com.hajdu.sp.competition.update.event.competition;

import com.hajdu.sp.competition.update.value.competition.turn.Turn;
import lombok.*;

import java.util.Collection;
import java.util.UUID;

@Builder
@Value
@AllArgsConstructor
public class TurnsAdded implements CompetitionEvent {

    @NonNull
    private UUID stageId;

    @NonNull
    private Turn turn;
}
