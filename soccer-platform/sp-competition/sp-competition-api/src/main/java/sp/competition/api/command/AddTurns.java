package sp.competition.api.command;

import lombok.*;
import sp.competition.api.value.StageId;
import sp.competition.api.value.season.Turn;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Value
@Builder
@AllArgsConstructor
public class AddTurns extends CompetitionCommand {

    @NonNull
    private StageId stageId;

    @Singular
    private Set<Turn> turnIds; // TODO maybe TurnCommand???

}
