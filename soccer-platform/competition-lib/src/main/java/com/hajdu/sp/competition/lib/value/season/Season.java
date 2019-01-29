package com.hajdu.sp.competition.lib.value.season;

import com.hajdu.sp.common.Interval;
import lombok.*;
import com.hajdu.sp.competition.lib.value.SeasonId;
import java.util.List;

@Value
@Builder
@AllArgsConstructor
public class Season {

    @NonNull
    private SeasonId id;

    @NonNull
    private String name;

    @Singular
    private List<Stage> stages;

    @NonNull
    private Interval interval;
}
