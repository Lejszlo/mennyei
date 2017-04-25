package com.mennyei.core.match.domain.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@Builder
public class SubstitutionEvent extends MatchEvent {
	private String inner;
	
	private String outer;
	
	public static SubstitutionEvent substutitionOf(String inner, String outer, int minute) {
		SubstitutionEvent substitutionEvent = SubstitutionEvent.builder().inner(inner).outer(outer).build();
		substitutionEvent.minute = minute;
		substitutionEvent.matchEventType = MatchEventType.SUBSTITUTION;
		return substitutionEvent;
	}
}
