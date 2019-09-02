package com.hajdu.sp.competition.update.value.competition.season;

import com.hajdu.sp.competition.update.util.Interval;
import com.hajdu.sp.competition.update.value.competition.SeasonInfo;
import com.hajdu.sp.competition.update.value.competition.ids.SeasonId;
import com.hajdu.sp.competition.update.value.competition.stage.Stages;
import lombok.*;

@Value
@Builder
@AllArgsConstructor
public class Season implements Comparable<Season> {

    @NonNull
    private SeasonInfo seasonInfo;

    @NonNull
    private SeasonId id;

    @NonNull
    private Stages stages;

    @NonNull
    private Interval interval;

    @Override
    public int compareTo(Season that) {
        return this.interval.compareTo(that.getInterval());
    }
}
