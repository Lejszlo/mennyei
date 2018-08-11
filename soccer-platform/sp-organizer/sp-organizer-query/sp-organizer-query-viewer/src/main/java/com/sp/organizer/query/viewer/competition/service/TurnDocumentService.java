package com.sp.organizer.query.viewer.competition.service;

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
import java.util.stream.Collectors;

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

    public Resources<TurnDocumentResource> getTurns(String competitionId, String stageId) {
        Optional<StageDocument> stageDocumentOptional = stageDocumentService.getStageDocument(competitionId, stageId);
        List<TurnDocument> turnDocuments = stageDocumentOptional.map(stageDocument -> stageDocument.getTurns()
                    .stream()
                    .sorted()
                    .collect(Collectors.toList()))
                .orElseGet(Collections::emptyList);
        return new Resources<>(turnDocumentResourceAssemblerSupport.toResources(turnDocuments));
    }

    public TurnDocumentResource getTurn(String competitionId, String stageId, int turnIndex) {
        return getTurnDocument(competitionId, stageId, turnIndex)
                .map(turnDocumentResourceAssemblerSupport::toResource)
                .orElse(TurnDocumentResource.builder().build());
    }

    private Optional<TurnDocument> getTurnDocument(String competitionId, String stageId, int turnIndex) {
        Optional<StageDocument> stageDocuments = stageDocumentService.getStageDocument(competitionId, stageId);
        return stageDocuments
                .flatMap(sd -> sd.getTurns()
                        .stream()
                        .filter(td -> td.getIndex() == turnIndex)
                        .findFirst());
    }
}
