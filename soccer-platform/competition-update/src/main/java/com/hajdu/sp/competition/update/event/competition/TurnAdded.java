package com.hajdu.sp.competition.update.event.competition;

import com.hajdu.sp.competition.update.value.competition.ids.StageId;
import com.hajdu.sp.competition.update.value.competition.turn.Turn;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Builder
@Value
@AllArgsConstructor
public class TurnAdded implements CompetitionEvent {

    @NonNull
    private StageId stageId;

    @NonNull
    private Turn turn;
}
