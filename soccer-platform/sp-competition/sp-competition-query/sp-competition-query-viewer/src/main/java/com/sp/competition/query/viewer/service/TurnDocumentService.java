package com.sp.competition.query.viewer.service;

import com.sp.competition.query.updater.entity.StageDocument;
import com.sp.competition.query.updater.entity.TurnDocument;
import com.sp.competition.query.viewer.resource.turn.TurnDocumentResourceAssemblerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Service;
import sp.competition.api.resource.TurnDocumentResource;
import sp.competition.api.value.StageId;

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

    public Resources<TurnDocumentResource> getTurns(StageId stageId) {
        Optional<StageDocument> stageDocumentOptional = stageDocumentService.getStageDocument(stageId);
        List<TurnDocument> turnDocuments = stageDocumentOptional.map(stageDocument -> stageDocument.getTurns()
                    .stream()
                    .sorted()
                    .collect(Collectors.toList()))
                .orElseGet(Collections::emptyList);
        return new Resources<>(turnDocumentResourceAssemblerSupport.toResources(turnDocuments));
    }

    public TurnDocumentResource getTurn(StageId stageId, int turnIndex) {
        return getTurnDocument(stageId, turnIndex)
                .map(turnDocumentResourceAssemblerSupport::toResource)
                .orElse(TurnDocumentResource.builder().build());
    }

    private Optional<TurnDocument> getTurnDocument(StageId stageId, int turnIndex) {
        Optional<StageDocument> stageDocuments = stageDocumentService.getStageDocument(stageId);
        return stageDocuments
                .flatMap(sd -> sd.getTurns()
                        .stream()
                        .filter(td -> td.getIndex() == turnIndex)
                        .findFirst());
    }
}
