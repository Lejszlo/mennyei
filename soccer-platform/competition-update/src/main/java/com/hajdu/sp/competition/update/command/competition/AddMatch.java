package com.hajdu.sp.competition.update.command.competition;

import com.hajdu.sp.competition.update.util.Interval;
import com.hajdu.sp.competition.update.value.competition.ids.TurnId;
import com.hajdu.sp.competition.update.value.match.MatchId;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder
@EqualsAndHashCode(callSuper = false)
public class AddMatch extends CompetitionCommand {
	@NonNull
	TurnId turnId;

	@NonNull
	MatchId matchId;

	Interval interval;
}
