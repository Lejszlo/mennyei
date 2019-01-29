package com.hajdu.sp.competition.lib.command;

import com.hajdu.sp.common.Interval;
import com.hajdu.sp.match.lib.value.MatchId;
import lombok.*;
import com.hajdu.sp.competition.lib.value.TurnId;

import java.util.Collection;

@Value
@Builder
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class AddMatches extends CompetitionCommand {
	@NonNull
	TurnId turnId;

	@Singular
	Collection<MatchId> matches;

	Interval interval;
}
