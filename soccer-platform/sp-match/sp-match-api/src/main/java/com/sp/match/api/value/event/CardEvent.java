package com.sp.match.api.value.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@Builder
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
public class CardEvent extends MatchEvent {
	private String suffererId;

	public static CardEvent yellowCardOf(int minute) {
		return yellowCardOf("", minute);
	}

	public static CardEvent redCardOf(int minute) {
		return redCardOf("", minute);
	}

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
