package com.hajdu.sp.competition.update.event.club;

import com.hajdu.sp.competition.update.event.competition.CompetitionEvent;
import com.hajdu.sp.competition.update.value.club.ClubId;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Builder
@Value
@AllArgsConstructor
public class ClubsAdded implements CompetitionEvent {

    @NonNull
    private UUID stageId;

    @NonNull
    private UUID seasonId;

    @NonNull
    private ClubId club;
}
