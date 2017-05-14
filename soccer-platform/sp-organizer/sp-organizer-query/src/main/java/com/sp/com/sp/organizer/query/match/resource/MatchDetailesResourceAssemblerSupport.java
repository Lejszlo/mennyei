package com.sp.organizer.backend.match.resource;

import com.sp.core.backend.value.match.event.CardEvent;
import com.sp.core.backend.value.match.event.MatchEventType;
import com.sp.organizer.backend.match.controller.MatchController;
import com.sp.organizer.backend.match.dto.match.MatchQuery;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

public class MatchDetailesResourceAssemblerSupport extends ResourceAssemblerSupport<MatchQuery, MatchDetailesResource> {


	public MatchDetailesResourceAssemblerSupport() {
		super(MatchController.class, MatchDetailesResource.class);
	}

	@Override
	public MatchDetailesResource toResource(MatchQuery matchQuery) {
		MatchDetailesResource resource = createResourceWithId(matchQuery.getId(), matchQuery);
		resource.setTotalRedCardAmount(matchQuery.getMatchResultDetailes().filterEvents(CardEvent.class, MatchEventType.RED_CARD).size());
		resource.setTotalYellowCardAmount(matchQuery.getMatchResultDetailes().filterEvents(CardEvent.class, MatchEventType.YELLOW_CARD).size());
		return resource;
	}


}
