package com.sp.organizer.api.competition;

import com.sp.organizer.api.value.competition.season.Stage;
import lombok.*;

@Value
@Builder
@AllArgsConstructor
public class AddStageCommand extends CompetitionCommand {

    @NonNull
    private Stage stage;
}
