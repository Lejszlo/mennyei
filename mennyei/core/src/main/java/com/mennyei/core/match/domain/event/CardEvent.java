package com.mennyei.core.match.domain.event;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@Builder
@EqualsAndHashCode(callSuper=false)
public class CardEvent extends MatchEvent {
	private String suffererId;
	
	public static CardEvent yellowCardOf(String suffererId, int minute) {
		CardEvent cardEvent = CardEvent.builder().suffererId(suffererId).build();
		cardEvent.minute = minute;
		cardEvent.matchEventType = MatchEventType.YELLOW_CARD;
		return cardEvent;
	}
	
	public static CardEvent redCardOf(String suffererId, int minute) {
		CardEvent cardEvent = CardEvent.builder().suffererId(suffererId).build();
		cardEvent.minute = minute;
		cardEvent.matchEventType = MatchEventType.RED_CARD;
		return cardEvent;
	}
}
