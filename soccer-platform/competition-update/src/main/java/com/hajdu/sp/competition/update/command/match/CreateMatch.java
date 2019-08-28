package com.hajdu.sp.competition.update.command.match;

import com.hajdu.sp.competition.update.value.match.MatchInfo;
import lombok.*;

@Value
@Builder
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CreateMatch extends MatchCommand {

	@NonNull
	private MatchInfo matchInfo;
	
}
