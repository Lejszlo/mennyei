package com.sp.organizer.api.event.competition;

import com.sp.organizer.api.value.competition.season.Season;
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
