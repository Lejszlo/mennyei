package com.hajdu.sp.competition.query.resource.turn;

import com.hajdu.sp.competition.lib.resource.TurnDocumentResource;
import com.hajdu.sp.competition.query.entity.TurnDocument;
import com.hajdu.sp.match.lib.controller.MatchDocumentQueryController;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

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

        turnDocumentResource.add(linkTo(ControllerLinkBuilder.methodOn(MatchDocumentQueryController.class).getMatches(turnDocument.getMatcheIds())).withRel("matches"));

        return turnDocumentResource;
    }
}
