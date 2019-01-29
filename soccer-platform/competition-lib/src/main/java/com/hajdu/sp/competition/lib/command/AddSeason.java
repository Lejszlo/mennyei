package com.hajdu.sp.competition.lib.command;

import com.hajdu.sp.common.Interval;
import com.hajdu.sp.competition.lib.value.SeasonId;
import com.hajdu.sp.competition.lib.value.season.Stage;
import lombok.*;

import java.util.List;

@Value
@Builder
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class AddSeason extends CompetitionCommand {
    @NonNull
    private SeasonId seasonId;

    @NonNull
    private String name;

    @NonNull
    private Interval interval;

    @Singular
    private List<Stage> stages;

}
