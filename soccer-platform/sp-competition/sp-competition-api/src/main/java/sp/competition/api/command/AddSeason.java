package sp.competition.api.command;

import lombok.*;
import sp.common.Interval;
import sp.competition.api.value.SeasonId;
import sp.competition.api.value.season.Stage;

import javax.validation.constraints.NotNull;
import java.util.List;

@Value
@Builder
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class AddSeason extends CompetitionCommand {
    @NonNull
    private SeasonId seasonId;

    @NotNull
    private String name;

    @NonNull
    private Interval interval;

    @Singular
    private List<Stage> stages;

}
