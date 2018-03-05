package com.sp.organizer.query.viewer.competition.service;

import com.sp.match.api.controller.MatchDocumentControllerApi;
import com.sp.match.api.resource.MatchDocumentResource;
import com.sp.organizer.query.updater.competition.entity.StageDocument;
import com.sp.organizer.query.updater.competition.entity.TurnDocument;
import com.sp.organizer.query.viewer.competition.resource.turn.TurnDocumentResource;
import com.sp.organizer.query.viewer.competition.resource.turn.TurnDocumentResourceAssemblerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class TurnDocumentService {
    private StageDocumentService stageDocumentService;
    private TurnDocumentResourceAssemblerSupport turnDocumentResourceAssemblerSupport;

    @Autowired
    public TurnDocumentService(StageDocumentService stageDocumentService,
                               TurnDocumentResourceAssemblerSupport turnDocumentResourceAssemblerSupport) {
        this.stageDocumentService = stageDocumentService;
        this.turnDocumentResourceAssemblerSupport = turnDocumentResourceAssemblerSupport;
    }

    public Resources<TurnDocumentResource> getTurns(String competitionId, int stageIndex) {
        Optional<StageDocument> stageDocumentOptional = stageDocumentService.getStageQuery(competitionId, stageIndex);
        List<TurnDocument> turnDocuments = stageDocumentOptional.map(StageDocument::getTurns).orElseGet(Collections::emptyList);
        return new Resources<>(turnDocumentResourceAssemblerSupport.toResources(turnDocuments));
    }

    public TurnDocumentResource getTurn(String competitionId, int stageIndex, int turnIndex) {
        return getTurnDocument(competitionId, stageIndex, turnIndex)
                .map(turnDocumentResourceAssemblerSupport::toResource)
                .orElse(TurnDocumentResource.builder().build());
    }

    private Optional<TurnDocument> getTurnDocument(String competitionId, int stageIndex, int turnIndex) {
        Optional<StageDocument> stageDocuments = stageDocumentService.getStageQuery(competitionId, stageIndex);
        return stageDocuments
                .flatMap(sd -> sd.getTurns()
                        .stream()
                        .filter(td -> td.getIndex() == turnIndex)
                        .findFirst());
    }
}
