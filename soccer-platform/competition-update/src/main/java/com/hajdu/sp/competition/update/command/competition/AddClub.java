package com.hajdu.sp.competition.update.command.competition;

import com.hajdu.sp.competition.update.value.club.ClubId;
import com.hajdu.sp.competition.update.value.competition.ids.StageId;
import lombok.*;

@Value
@EqualsAndHashCode(callSuper = true)
@Builder
public class AddClub extends CompetitionCommand {

    @NonNull
    StageId stageId;

    @NonNull
    ClubId clubId;
}

