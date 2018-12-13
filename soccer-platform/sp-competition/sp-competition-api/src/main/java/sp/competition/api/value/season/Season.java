package sp.competition.api.value.season;

import lombok.*;
import sp.common.Interval;
import sp.competition.api.value.SeasonId;

import javax.validation.constraints.NotNull;
import java.util.List;

@Value
@Builder
@AllArgsConstructor
public class Season {

    @NotNull
    private SeasonId id;

    @NotNull
    private String name;

    @Singular
    private List<Stage> stages;

    @NonNull
    private Interval interval;
}
