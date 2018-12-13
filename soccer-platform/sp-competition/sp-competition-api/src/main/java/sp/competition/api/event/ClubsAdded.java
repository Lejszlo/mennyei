package sp.competition.api.event;

import com.sp.club.api.value.ClubId;
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
