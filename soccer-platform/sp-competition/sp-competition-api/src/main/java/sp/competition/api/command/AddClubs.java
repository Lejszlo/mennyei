package sp.competition.api.command;

import com.sp.club.api.value.ClubId;
import lombok.*;
import sp.competition.api.value.StageId;

import java.util.Set;

@Value
@Builder
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class AddClubs extends CompetitionCommand {

    @NonNull
    StageId stageId;

    @Singular
    Set<ClubId> clubIds;
}

