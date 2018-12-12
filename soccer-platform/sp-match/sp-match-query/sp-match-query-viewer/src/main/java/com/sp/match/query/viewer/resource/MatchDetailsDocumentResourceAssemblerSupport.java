package com.sp.match.query.viewer.resource;

import com.sp.match.api.resource.MatchDetailsDocumentResource;
import com.sp.match.api.value.event.CardEvent;
import com.sp.match.api.value.event.MatchEventType;
import com.sp.match.query.updater.match.entity.MatchDocument;
import com.sp.match.query.viewer.controller.MatchDocumentQueryClient;
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
