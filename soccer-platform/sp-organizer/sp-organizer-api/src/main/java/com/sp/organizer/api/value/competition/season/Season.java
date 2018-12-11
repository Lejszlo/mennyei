package com.sp.organizer.api.value.competition.season;

import com.sp.core.query.configurations.Interval;
import com.sp.organizer.api.value.competition.SeasonId;
import lombok.*;

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
