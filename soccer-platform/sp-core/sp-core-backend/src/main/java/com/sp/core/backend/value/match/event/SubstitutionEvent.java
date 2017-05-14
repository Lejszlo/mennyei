package com.sp.core.backend.value.match.event;

import lombok.*;

@Value
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@Builder
public class SubstitutionEvent extends MatchEvent {

    @Getter
    private String inner;

	@Getter
	private String outer;
	
	public static SubstitutionEvent substutitionOf(String inner, String outer, int minute) {
		SubstitutionEvent substitutionEvent = SubstitutionEvent.builder().inner(inner).outer(outer).build();
		substitutionEvent.minute = minute;
		substitutionEvent.matchEventType = MatchEventType.SUBSTITUTION;
		return substitutionEvent;
	}
}
