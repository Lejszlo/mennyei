package event.competition;

import lombok.*;
import value.competition.season.Stage;

@Builder
@Value
@AllArgsConstructor
public class StageAdded implements CompetitionEvent {

    @NonNull
    private Stage stage;
}
