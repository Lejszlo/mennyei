package com.hajdu.sp.competition.update.event.competition;

import com.hajdu.sp.competition.update.value.competition.ids.TurnId;
import com.hajdu.sp.competition.update.value.match.MatchId;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@Builder
public class MatchAdded implements CompetitionEvent {
    MatchId matchId;

    TurnId turnId;
}
