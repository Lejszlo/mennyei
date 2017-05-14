package com.sp.organizer.backend.match.service;

import org.springframework.stereotype.Service;

@Service
public class MatchPlayerStatisticService {
	
//	@NonNull
//	public Set<MatchEvent> getEventsByPlayer(Match com.sp.organizer.backend.match, Player player, MatchEventType type) {
//		return com.sp.organizer.backend.match.getEvents().stream()
//							.filter(matchEvent -> type.equals(matchEvent.getMatchEventType()))
//							.collect(Collectors.toSet());
//	}
	
//	@NonNull
//	public Set<MatchEvent> getCardEventsByPlayer(Match com.sp.organizer.backend.match, Player player, CardEventType type) {
//		Set<MatchEvent> player.events = getEventsByPlayer(com.sp.organizer.backend.match, player, MatchEventType.CARD);
//		return player.events.stream()
//					.map(event -> (CardEvent) event)
//					.filter(event -> type.equals(event.getType()))
//					.collect(Collectors.toSet());
//	}
//	
//	@NonNull
//	public Optional<Substitution> getSubstutuionInEventsByPlayer(Match com.sp.organizer.backend.match, Player player) {
//		Set<MatchEvent> player.events = getEventsByPlayer(com.sp.organizer.backend.match, player, MatchEventType.SUBSTITUTION);
//		return player.events.stream()
//						.map(event -> (Substitution) event)
//						.filter(event -> player.equals(event.getInner()))
//						.findFirst();
//	}
//	
//	@NonNull
//	public int playerWasSubstutuionIn(Match com.sp.organizer.backend.match, Player player) {
//		return getSubstutuionInEventsByPlayer(com.sp.organizer.backend.match, player).isPresent() ? 1 : 0;
//	}
	
//	@NonNull
//	public Optional<Substitution> getSubstutuionOutEventsByPlayer(Match com.sp.organizer.backend.match, Player player) {
//		Set<MatchEvent> player.events = getEventsByPlayer(com.sp.organizer.backend.match, player, MatchEventType.SUBSTITUTION);
//		return player.events.stream()
//						.map(event -> (Substitution) event)
//						.filter(event -> player.equals(event.getOuter()))
//						.findFirst();
//	}
	
//	@NonNull
//	public int playerWasSubstutuionOut(Match com.sp.organizer.backend.match, Player player) {
//		return getSubstutuionOutEventsByPlayer(com.sp.organizer.backend.match, player).isPresent() ? 1 : 0;
//	}
	
}
