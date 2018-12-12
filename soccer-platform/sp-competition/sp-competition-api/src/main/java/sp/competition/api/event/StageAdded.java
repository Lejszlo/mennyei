package sp.competition.api.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import sp.competition.api.value.season.Stage;

@Builder
@Value
@AllArgsConstructor
public class StageAdded implements CompetitionEvent {

    @NonNull
    private Stage stage;
}
