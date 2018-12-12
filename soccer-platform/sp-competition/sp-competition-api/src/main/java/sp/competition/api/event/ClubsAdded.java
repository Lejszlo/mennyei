package sp.competition.api.event;

import lombok.*;

import java.util.Collection;
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
    private Collection<String> clubIds;
}
