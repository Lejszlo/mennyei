package com.sp.organizer.api.command.competition;

import com.sp.core.backend.web.resource.IdResource;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Value
@Builder
@AllArgsConstructor
public class AddClubsToStageCommand extends CompetitionCommand {

    @NonNull
    private UUID stageId;

    @Singular
    private Set<UUID> clubIds;
}
