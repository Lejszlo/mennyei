package com.hajdu.sp.competition.update.value.competition.season;

import com.hajdu.sp.competition.update.util.Interval;
import com.hajdu.sp.competition.update.value.competition.CompetitionInfo;
import com.hajdu.sp.competition.update.value.competition.ids.SeasonId;
import com.hajdu.sp.competition.update.value.competition.stage.Stages;
import lombok.*;

@Value
@Builder
@AllArgsConstructor
public class Season {

    @NonNull
    private CompetitionInfo competitionInfo;

    @NonNull
    private SeasonId id;

    @NonNull
    private String name;

    private Stages stages;

    @NonNull
    private Interval interval;
}
