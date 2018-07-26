package com.sp.organizer.api.command.competition;

import com.sp.organizer.api.value.competition.StageId;
import lombok.*;

import java.util.Set;

@Value
@Builder
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class AddClubsCompetitionCommand extends CompetitionCommand {

    @NonNull
    private StageId stageId;

    @Singular
    private Set<String> clubIds;
}

