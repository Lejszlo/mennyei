package com.hajdu.sp.competition.lib.event;

import com.hajdu.sp.competition.lib.value.season.Stage;
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
}
