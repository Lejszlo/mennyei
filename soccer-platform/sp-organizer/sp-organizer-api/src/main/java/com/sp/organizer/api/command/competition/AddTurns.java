package com.sp.organizer.api.command.competition;

import com.sp.organizer.api.value.competition.StageId;
import com.sp.organizer.api.value.competition.season.Turn;
import lombok.*;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Value
@Builder
@AllArgsConstructor
public class AddTurns extends CompetitionCommand {

    @NonNull
    private StageId stageId;

    @Singular
    private Set<Turn> turnIds; // TODO maybe TurnCommand???

}
