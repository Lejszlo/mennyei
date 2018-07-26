package com.sp.organizer.api.event.competition;

import lombok.*;

import java.util.Collection;
import java.util.UUID;

@Builder
@Value
@AllArgsConstructor
public class ClubsAdded implements CompetitionEvent {

    @NonNull
    private UUID stageId;

    @Singular
    private Collection<String> clubIds;
}
