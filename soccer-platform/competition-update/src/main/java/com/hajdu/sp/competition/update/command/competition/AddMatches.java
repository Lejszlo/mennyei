package com.hajdu.sp.competition.update.command.competition;

import com.hajdu.sp.competition.update.util.Interval;
import com.hajdu.sp.competition.update.value.match.MatchId;
import com.hajdu.sp.competition.update.value.competition.ids.TurnId;
import lombok.*;

import java.util.Collection;

@Value
@Builder
@EqualsAndHashCode(callSuper = false)
public class AddMatches extends CompetitionCommand {
	@NonNull
	TurnId turnId;

	@NonNull
	MatchId match;

	Interval interval;
}
