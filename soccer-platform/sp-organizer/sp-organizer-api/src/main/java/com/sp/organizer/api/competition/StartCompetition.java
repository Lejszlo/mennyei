package com.sp.organizer.api.competition;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class StartCompetition extends CompetitionCommand {
    private String competitionId;
}
