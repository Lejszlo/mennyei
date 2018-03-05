package com.sp.organizer.api.event.competition;

import com.sp.organizer.api.value.competition.season.Stage;
import lombok.*;

@Builder
@Value
@AllArgsConstructor
public class StageAdded implements CompetitionEvent {

    @NonNull
    private Stage stage;
}
