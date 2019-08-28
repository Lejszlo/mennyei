package com.hajdu.sp.competition.update.event.competition;

import com.hajdu.sp.competition.update.value.competition.season.Season;
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
