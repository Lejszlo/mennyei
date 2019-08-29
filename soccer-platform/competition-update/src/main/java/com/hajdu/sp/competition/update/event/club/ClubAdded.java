package com.hajdu.sp.competition.update.event.club;

import com.hajdu.sp.competition.update.event.competition.CompetitionEvent;
import com.hajdu.sp.competition.update.value.club.ClubId;
import com.hajdu.sp.competition.update.value.competition.ids.StageId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Builder
@Value
@AllArgsConstructor
public class ClubAdded implements CompetitionEvent {

    @NonNull
    private StageId stageId;

    @NonNull
    private ClubId clubId;
}
