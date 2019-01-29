package com.hajdu.sp.match.lib.command;

import com.hajdu.sp.match.lib.value.MatchInfo;
import lombok.*;

@Value
@Builder
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CreateMatch extends MatchCommand {

	@NonNull
	private MatchInfo matchInfo;
	
}
