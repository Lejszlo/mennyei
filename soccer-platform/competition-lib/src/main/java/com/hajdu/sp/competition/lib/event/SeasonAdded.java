package com.hajdu.sp.competition.lib.event;

import com.hajdu.sp.competition.lib.value.season.Season;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Builder
@Value
@AllArgsConstructor
public class SeasonAdded implements CompetitionEvent {

    @NonNull
    private Season season;
}
