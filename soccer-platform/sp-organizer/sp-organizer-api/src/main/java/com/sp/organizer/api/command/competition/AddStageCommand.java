package com.sp.organizer.api.command.competition;

import com.sp.organizer.api.value.competition.season.Stage;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Value
@Builder
@AllArgsConstructor
public class AddStageCommand extends CompetitionCommand {

    @NonNull
    private Stage stage;
}
