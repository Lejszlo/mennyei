package com.mennyei.core.competition.service;

import org.springframework.stereotype.Service;

@Service
public class MatchPlayerStatisticService {
	
//	@NonNull
//	public Set<MatchEvent> getEventsByPlayer(Match match, Player player, MatchEventType type) {
//		return match.getEvents().stream()
//							.filter(matchEvent -> type.equals(matchEvent.getMatchEventType()))
//							.collect(Collectors.toSet());
//	}
	
//	@NonNull
//	public Set<MatchEvent> getCardEventsByPlayer(Match match, Player player, CardEventType type) {
//		Set<MatchEvent> events = getEventsByPlayer(match, player, MatchEventType.CARD);
//		return events.stream()
//					.map(event -> (CardEvent) event)
//					.filter(event -> type.equals(event.getType()))
//					.collect(Collectors.toSet());
//	}
//	
//	@NonNull
//	public Optional<Substitution> getSubstutuionInEventsByPlayer(Match match, Player player) {
//		Set<MatchEvent> events = getEventsByPlayer(match, player, MatchEventType.SUBSTITUTION);
//		return events.stream()
//						.map(event -> (Substitution) event)
//						.filter(event -> player.equals(event.getInner()))
//						.findFirst();
//	}
//	
//	@NonNull
//	public int playerWasSubstutuionIn(Match match, Player player) {
//		return getSubstutuionInEventsByPlayer(match, player).isPresent() ? 1 : 0;
//	}
	
//	@NonNull
//	public Optional<Substitution> getSubstutuionOutEventsByPlayer(Match match, Player player) {
//		Set<MatchEvent> events = getEventsByPlayer(match, player, MatchEventType.SUBSTITUTION);
//		return events.stream()
//						.map(event -> (Substitution) event)
//						.filter(event -> player.equals(event.getOuter()))
//						.findFirst();
//	}
	
//	@NonNull
//	public int playerWasSubstutuionOut(Match match, Player player) {
//		return getSubstutuionOutEventsByPlayer(match, player).isPresent() ? 1 : 0;
//	}
	
}