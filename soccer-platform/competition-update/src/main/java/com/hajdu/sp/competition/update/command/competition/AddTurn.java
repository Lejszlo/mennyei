package com.hajdu.sp.competition.update.command.competition;

import com.hajdu.sp.competition.update.value.competition.ids.StageId;
import com.hajdu.sp.competition.update.value.competition.turn.Turn;
import lombok.*;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Value
@Builder
public class AddTurn extends CompetitionCommand {

    @NonNull
    StageId stageId;
    
    @NonNull
    Turn turn;

}
