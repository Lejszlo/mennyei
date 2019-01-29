package com.hajdu.sp.competition.lib.event;

import com.hajdu.sp.club.lib.value.ClubId;
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

    @Singular
    private Set<ClubId> clubIds;
}
