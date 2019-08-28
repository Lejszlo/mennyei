package com.hajdu.sp.competition.update.value.match.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@Builder
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
public class CardEvent extends GameEvent {
	private String suffererId;

	public static CardEvent yellowCardOf(int minute) {
		return yellowCardOf("", minute);
	}

	public static CardEvent redCardOf(int minute) {
		return redCardOf("", minute);
	}

	public static CardEvent yellowCardOf(String suffererId, int minute) {
		CardEvent cardEvent = CardEvent.builder().suffererId(suffererId).build();
		cardEvent.setMinute(minute);
		cardEvent.setMatchEventType(MatchEventType.YELLOW_CARD);
		return cardEvent;
	}
	
	public static CardEvent redCardOf(String suffererId, int minute) {
		CardEvent cardEvent = CardEvent.builder().suffererId(suffererId).build();
		cardEvent.setMinute(minute);
		cardEvent.setMatchEventType(MatchEventType.RED_CARD);
		return cardEvent;
	}
}
