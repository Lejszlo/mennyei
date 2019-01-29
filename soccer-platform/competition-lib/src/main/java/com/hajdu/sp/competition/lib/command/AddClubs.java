package com.hajdu.sp.competition.lib.command;

import com.hajdu.sp.club.lib.value.ClubId;
import lombok.*;
import com.hajdu.sp.competition.lib.value.StageId;

import java.util.Set;

@Value
@Builder
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class AddClubs extends CompetitionCommand {

    @NonNull
    StageId stageId;

    @Singular
    Set<ClubId> clubIds;
}

