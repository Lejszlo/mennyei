package com.hajdu.sp.competition.update.event.competition;

import com.hajdu.sp.competition.update.value.competition.ids.SeasonId;
import com.hajdu.sp.competition.update.value.competition.stage.Stage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Builder
@Value
@AllArgsConstructor
public class StageAdded implements CompetitionEvent {

    @NonNull
    private Stage stage;

    @NonNull
    private SeasonId seasonId;
}
