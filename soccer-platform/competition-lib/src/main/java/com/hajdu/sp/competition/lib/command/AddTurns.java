package com.hajdu.sp.competition.lib.command;

import com.hajdu.sp.competition.lib.value.season.Turn;
import lombok.*;
import com.hajdu.sp.competition.lib.value.StageId;

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
