package com.hajdu.sp.match.query.resource;

import com.hajdu.sp.match.lib.resource.MatchDetailsDocumentResource;
import com.hajdu.sp.match.lib.value.event.CardEvent;
import com.hajdu.sp.match.lib.value.event.MatchEventType;
import com.hajdu.sp.match.query.controller.MatchDocumentQueryClient;
import com.hajdu.sp.match.query.entity.MatchDocument;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class MatchDetailsDocumentResourceAssemblerSupport extends ResourceAssemblerSupport<MatchDocument, MatchDetailsDocumentResource> {

	public MatchDetailsDocumentResourceAssemblerSupport() {
		super(MatchDocumentQueryClient.class, MatchDetailsDocumentResource.class);
	}

	@Override
	public MatchDetailsDocumentResource toResource(MatchDocument matchDocument) {
		MatchDetailsDocumentResource resource = createResourceWithId(matchDocument.getId(), matchDocument);
		resource.setTotalRedCardAmount(matchDocument.getMatchResultDetails().filterEvents(CardEvent.class, MatchEventType.RED_CARD).size());
		resource.setTotalYellowCardAmount(matchDocument.getMatchResultDetails().filterEvents(CardEvent.class, MatchEventType.YELLOW_CARD).size());
		return resource;
	}


}
