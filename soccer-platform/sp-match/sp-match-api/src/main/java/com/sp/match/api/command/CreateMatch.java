package com.sp.match.api.command;

import com.sp.match.api.value.MatchInfo;
import lombok.*;

@Value
@Builder
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CreateMatch extends MatchCommand {

	@NonNull
	private MatchInfo matchInfo;
	
}
