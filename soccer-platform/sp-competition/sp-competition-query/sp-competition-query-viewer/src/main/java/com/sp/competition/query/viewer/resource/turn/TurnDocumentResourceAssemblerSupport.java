package com.sp.competition.query.viewer.resource.turn;

import com.sp.competition.query.updater.entity.TurnDocument;
import com.sp.match.api.controller.MatchDocumentQueryController;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;
import sp.competition.api.resource.TurnDocumentResource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class TurnDocumentResourceAssemblerSupport extends ResourceAssemblerSupport<TurnDocument, TurnDocumentResource> {

    public TurnDocumentResourceAssemblerSupport() {
        super(MatchDocumentQueryController.class, TurnDocumentResource.class);
    }

    @Override
    public TurnDocumentResource toResource(TurnDocument turnDocument) {
        TurnDocumentResource turnDocumentResource = TurnDocumentResource.builder()
                .index(turnDocument.getIndex())
                .interval(turnDocument.getInterval())
                .build();

        turnDocumentResource.add(linkTo(methodOn(MatchDocumentQueryController.class).getMatches(turnDocument.getMatcheIds())).withRel("matches"));

        return turnDocumentResource;
    }
}
