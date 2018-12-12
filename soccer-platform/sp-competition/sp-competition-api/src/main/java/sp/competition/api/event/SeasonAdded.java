package sp.competition.api.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import sp.competition.api.value.season.Season;

@Builder
@Value
@AllArgsConstructor
public class SeasonAdded implements CompetitionEvent {

    @NonNull
    private Season season;
}
