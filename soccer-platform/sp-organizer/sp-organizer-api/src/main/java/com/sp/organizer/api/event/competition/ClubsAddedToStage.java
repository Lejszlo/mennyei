package com.sp.organizer.api.event.competition;

import com.sp.core.backend.web.resource.IdResource;
import lombok.*;

import java.util.Collection;
import java.util.UUID;

@Builder
@Value
@AllArgsConstructor
public class ClubsAddedToStage implements CompetitionEvent {

    @NonNull
    private UUID stageId;

    @Singular
    private Collection<UUID> clubIds;
}
