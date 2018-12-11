package com.sp.organizer.api.command.competition;

import com.sp.core.query.configurations.Interval;
import com.sp.organizer.api.value.competition.SeasonId;
import com.sp.organizer.api.value.competition.season.Stage;
import lombok.*;

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
