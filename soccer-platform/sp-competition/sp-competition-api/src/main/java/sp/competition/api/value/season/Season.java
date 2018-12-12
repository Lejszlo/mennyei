package sp.competition.api.value.season;

import com.sp.core.query.configurations.Interval;
import lombok.*;
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
