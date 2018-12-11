package com.sp.organizer.api.command.competition;

import com.sp.organizer.api.value.competition.StageId;
import lombok.*;

import java.util.Set;

@Value
@Builder
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class AddClubs extends CompetitionCommand {

    @NonNull
    private StageId stageId;

    @Singular
    private Set<String> clubIds;
}

