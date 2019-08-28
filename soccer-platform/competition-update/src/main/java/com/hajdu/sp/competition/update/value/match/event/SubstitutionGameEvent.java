package com.hajdu.sp.competition.update.value.match.event;

import lombok.*;

@Value
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@Builder
public class SubstitutionGameEvent extends GameEvent {

    @Getter
    private String inner;

	@Getter
	private String outer;
	
	public static SubstitutionGameEvent substutitionOf(String inner, String outer, int minute) {
		SubstitutionGameEvent substitutionGameEvent = SubstitutionGameEvent.builder().inner(inner).outer(outer).build();
		substitutionGameEvent.setMinute(minute);
		substitutionGameEvent.setMatchEventType(MatchEventType.SUBSTITUTION);
		return substitutionGameEvent;
	}
}
