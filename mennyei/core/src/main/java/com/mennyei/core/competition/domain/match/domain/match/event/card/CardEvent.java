package com.mennyei.core.competition.domain.match.domain.match.event.card;

import com.mennyei.core.competition.domain.match.domain.match.event.MatchEvent;
import com.mennyei.core.competition.domain.match.domain.match.event.MatchEventType;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@Builder
@EqualsAndHashCode(callSuper=false)
public class CardEvent extends MatchEvent {
	private String suffererId;
	
	private CardEventType type;
	
	public static CardEvent yellowCardOf(String suffererId, int minute) {
		CardEvent cardEvent = CardEvent.builder().suffererId(suffererId).type(CardEventType.YELLOW).build();
		cardEvent.minute = minute;
		cardEvent.matchEventType = MatchEventType.CARD;
		return cardEvent;
	}
	
	public static CardEvent redCardOf(String suffererId, int minute) {
		CardEvent cardEvent = CardEvent.builder().suffererId(suffererId).type(CardEventType.RED).build();
		cardEvent.minute = minute;
		cardEvent.matchEventType = MatchEventType.CARD;
		return cardEvent;
	}
}
