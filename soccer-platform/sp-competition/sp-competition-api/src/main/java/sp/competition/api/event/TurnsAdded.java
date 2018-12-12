package sp.competition.api.event;

import lombok.*;
import sp.competition.api.value.season.Turn;

import java.util.Collection;
import java.util.UUID;

@Builder
@Value
@AllArgsConstructor
public class TurnsAdded implements CompetitionEvent {

    @NonNull
    private UUID stageId;

    @Singular
    private Collection<Turn> turns;
}
