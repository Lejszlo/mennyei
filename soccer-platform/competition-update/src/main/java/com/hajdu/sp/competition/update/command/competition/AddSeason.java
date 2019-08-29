package com.hajdu.sp.competition.update.command.competition;

import com.hajdu.sp.competition.update.util.Interval;
import com.hajdu.sp.competition.update.value.competition.SeasonInfo;
import com.hajdu.sp.competition.update.value.competition.ids.SeasonId;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder
@EqualsAndHashCode(callSuper = true)
public class AddSeason extends CompetitionCommand {

    @NonNull
    SeasonInfo seasonInfo;

    @NonNull
    SeasonId seasonId;

    @NonNull
    Interval interval;
}
