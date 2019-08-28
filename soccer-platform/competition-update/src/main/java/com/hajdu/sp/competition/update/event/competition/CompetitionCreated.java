package com.hajdu.sp.competition.update.event.competition;

import com.hajdu.sp.competition.update.value.competition.organizer.Organizer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Builder
@Value
@AllArgsConstructor
public class CompetitionCreated implements CompetitionEvent {
    @NonNull
    Organizer organizer;
}
