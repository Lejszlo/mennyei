package com.sp.organizer.query.viewer.match.resource;

import com.sp.organizer.query.viewer.match.controller.MatchQueryController;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import sp.match.api.value.event.CardEvent;
import sp.match.api.value.event.MatchEventType;
import com.sp.organizer.query.updater.match.entity.MatchQuery;

public class MatchDetailesResourceAssemblerSupport extends ResourceAssemblerSupport<MatchQuery, MatchDetailesResource> {


	public MatchDetailesResourceAssemblerSupport() {
		super(MatchQueryController.class, MatchDetailesResource.class);
	}

	@Override
	public MatchDetailesResource toResource(MatchQuery matchQuery) {
		MatchDetailesResource resource = createResourceWithId(matchQuery.getId(), matchQuery);
		resource.setTotalRedCardAmount(matchQuery.getMatchResultDetailes().filterEvents(CardEvent.class, MatchEventType.RED_CARD).size());
		resource.setTotalYellowCardAmount(matchQuery.getMatchResultDetailes().filterEvents(CardEvent.class, MatchEventType.YELLOW_CARD).size());
		return resource;
	}


}
