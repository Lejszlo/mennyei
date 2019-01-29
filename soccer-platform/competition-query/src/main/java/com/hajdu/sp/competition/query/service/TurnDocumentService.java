package com.hajdu.sp.competition.query.service;

import com.hajdu.sp.competition.lib.resource.TurnDocumentResource;
import com.hajdu.sp.competition.lib.value.StageId;
import com.hajdu.sp.competition.query.entity.StageDocument;
import com.hajdu.sp.competition.query.entity.TurnDocument;
import com.hajdu.sp.competition.query.resource.turn.TurnDocumentResourceAssemblerSupport;
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
