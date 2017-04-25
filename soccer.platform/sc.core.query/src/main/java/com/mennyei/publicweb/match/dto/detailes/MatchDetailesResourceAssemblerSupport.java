package com.mennyei.publicweb.match.dto.detailes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.mennyei.core.match.domain.event.CardEvent;
import com.mennyei.core.match.domain.event.MatchEventType;
import com.mennyei.core.match.domain.event.lineup.LineUpType;
import com.mennyei.publicweb.match.controller.MatchController;
import com.mennyei.publicweb.match.dto.lineup.LineUpQuery;
import com.mennyei.publicweb.match.dto.lineup.LineUpResource;
import com.mennyei.publicweb.match.dto.match.MatchQuery;

public class MatchDetailesResourceAssemblerSupport extends ResourceAssemblerSupport<MatchQuery, MatchDetailesResource> {


	public MatchDetailesResourceAssemblerSupport() {
		super(MatchController.class, MatchDetailesResource.class);
	}

	@Override
	public MatchDetailesResource toResource(MatchQuery matchQuery) {
		MatchDetailesResource resource = createResourceWithId(matchQuery.getId(), matchQuery);
		
		List<LineUpResource> awayLineUps = matchQuery.getAwayLineUps().stream().map(LineUp -> createLineUpResource(LineUp, matchQuery)).collect(Collectors.toList());
		resource.setAwayStarters(awayLineUps.stream().filter(r -> LineUpType.STARTER.equals(r.getLineUpType())).collect(Collectors.toList()));
		resource.setAwaySubstitution(awayLineUps.stream().filter(r -> LineUpType.SUBSTITUTION.equals(r.getLineUpType())).collect(Collectors.toList()));
		
		List<LineUpResource> homeLineUps = matchQuery.getHomeLineUps().stream().map(LineUp -> createLineUpResource(LineUp, matchQuery)).collect(Collectors.toList());
		resource.setHomeStarters(homeLineUps.stream().filter(r -> LineUpType.STARTER.equals(r.getLineUpType())).collect(Collectors.toList()));
		resource.setHomeSubstitution(homeLineUps.stream().filter(r -> LineUpType.SUBSTITUTION.equals(r.getLineUpType())).collect(Collectors.toList()));
		
		resource.setTotalRedCardAmount(matchQuery.getMatchResultDetailes().filterEvents(CardEvent.class, MatchEventType.RED_CARD).size());
		resource.setTotalYellowCardAmount(matchQuery.getMatchResultDetailes().filterEvents(CardEvent.class, MatchEventType.YELLOW_CARD).size());
		return resource;
	}
	
	private LineUpResource createLineUpResource(LineUpQuery lineUpQuery, MatchQuery matchQuery) {
		LineUpResource lineUpResource = new LineUpResource();
		lineUpResource.setLineUpType(lineUpQuery.getLineUpType());
		String playerId = lineUpQuery.getPlayerQuery().getId();
		lineUpResource.setPlayerId(playerId);
		lineUpResource.setPlayerName(lineUpQuery.getPlayerQuery().getName());
		lineUpResource.setShirtNumber(lineUpQuery.getShirtNumber());
		lineUpResource.setGoalEvents(matchQuery.getMatchResultDetailes().getGoalEventsForPlayer(playerId));
		lineUpResource.setCardEvents(matchQuery.getMatchResultDetailes().getCardEventsForPlayer(playerId));
		lineUpResource.setSubstitutionEvents(matchQuery.getMatchResultDetailes().getSubstitutionEventForPlayer(playerId));
		return lineUpResource;
	}


}
